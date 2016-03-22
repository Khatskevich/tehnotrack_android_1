package com.example.alexeykhatskevich.track_android_1;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private final WeakReference<LayoutInflater> mInflater;

    public SimpleRecyclerAdapter(LayoutInflater inflater) {
        mInflater = new WeakReference<LayoutInflater>(inflater);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = mInflater.get();
        if (inflater != null) {
            return new SimpleViewHolder(inflater.inflate(R.layout.cell_layout, parent, false));
        }
        else {
            throw new RuntimeException("Oooops, looks like activity is dead");
        }
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.setText(NumberToTextConverter.convert(position));
        if (position%2==0){
            holder.setBGColorById(R.color.grey);
        }else{
            holder.setBGColorById(R.color.black);
        }

    }

    @Override
    public int getItemCount() {
        return NumberToTextConverter.max_value;
    }
}