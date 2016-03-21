package com.example.alexeykhatskevich.track_android_1;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {

    public void setText(String text) {
        mText.setText(text);
    }

    private TextView mText;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        mText = (TextView)itemView.findViewById(R.id.text);
    }
}