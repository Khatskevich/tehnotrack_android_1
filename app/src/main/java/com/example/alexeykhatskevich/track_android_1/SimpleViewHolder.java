package com.example.alexeykhatskevich.track_android_1;


import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {

    public void setText(String text) {
        mText.setText(text);
    }

    public void setBGColorById(int colorId){
        rootView.setBackgroundColor(ContextCompat.getColor(rootView.getContext(), colorId));
    }

    private TextView mText;
    private View rootView;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        mText = (TextView)itemView.findViewById(R.id.text);
    }
}