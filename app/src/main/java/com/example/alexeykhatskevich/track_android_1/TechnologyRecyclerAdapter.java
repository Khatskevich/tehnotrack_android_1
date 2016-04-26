package com.example.alexeykhatskevich.track_android_1;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.alexeykhatskevich.track_android_1.models.Technology;
import com.example.alexeykhatskevich.track_android_1.server.ServerApi;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TechnologyRecyclerAdapter extends RecyclerView.Adapter<TechnologyViewHolder> {
    private static ArrayList<Technology> technologies;
    private final WeakReference<LayoutInflater> mInflater;
    private Context mContext = null;
    public TechnologyRecyclerAdapter(LayoutInflater inflater, Context context) {
        mInflater = new WeakReference<LayoutInflater>(inflater);
        mContext = context;
    }

    @Override
    public TechnologyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = mInflater.get();
        if (inflater != null) {
            return new TechnologyViewHolder(inflater.inflate(R.layout.cell_layout, parent, false));
        }
        else {
            throw new RuntimeException("Oooops, looks like activity is dead");
        }
    }

    @Override
    public void onBindViewHolder(TechnologyViewHolder holder, int position) {
        holder.fillWithTechnology( technologies.get(position));
        if ((position+1)%2==0){
            holder.setBGColorById(R.color.grey);
        }else{
            holder.setBGColorById(R.color.white);
        }

    }

    @Override
    public int getItemCount() {
        return technologies.size();
    }

    public static void renewData(){
        technologies = ServerApi.getTechnologies();
    }
}