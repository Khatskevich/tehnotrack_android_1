package com.example.alexeykhatskevich.track_android_1;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexeykhatskevich.track_android_1.models.Technology;

public class TechnologyViewHolder extends RecyclerView.ViewHolder {

    public void setText(String text) {
        mText.setText(text);
    }

    public void setBGColorById(int colorId){
        rootView.setBackgroundColor(ContextCompat.getColor(rootView.getContext(), colorId));
    }

    private TextView mText;
    private View rootView;
    private AsyncTask imageDownloadTask;

    public void setImageView(Bitmap imageView) {
        this.imageView.setImageBitmap(imageView);
    }

    private ImageView imageView;

    public TechnologyViewHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        mText = (TextView)itemView.findViewById(R.id.text);
        imageView = (ImageView)itemView.findViewById(R.id.imageView);
    }
    public TechnologyViewHolder(View itemView, Technology technology) {
        this(itemView);
        fillWithTechnology(technology);
    }
    public synchronized void fillWithTechnology(final Technology technology){
        if (imageDownloadTask!=null){
            imageDownloadTask.cancel(true);
        }
        setText(technology.title);
        imageView.setImageBitmap(null);
        Bitmap image = technology.getImageFromCache();
        if ( image != null){
            imageView.setImageBitmap(image);
            return;
        }
        imageDownloadTask = new AsyncTask() {
            Bitmap image;
            @Override
            protected Object doInBackground(Object[] params) {
                image = technology.getImage();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                imageView.setImageBitmap(image);
            }
        };
        imageDownloadTask.execute();
    }
}