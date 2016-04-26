package com.example.alexeykhatskevich.track_android_1;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexeykhatskevich.track_android_1.models.Technology;
import com.example.alexeykhatskevich.track_android_1.server.ServerApi;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TechnologyRecyclerAdapter extends RecyclerView.Adapter<TechnologyViewHolder> {
    public static ArrayList<Technology> technologies;
    private final WeakReference<LayoutInflater> mInflater;
    private Context mContext = null;
    private FragmentManager fragmentManager;

    public TechnologyRecyclerAdapter(LayoutInflater inflater, Context context, FragmentManager fragmentManager) {
        mInflater = new WeakReference<LayoutInflater>(inflater);
        mContext = context;
        this.fragmentManager = fragmentManager;
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
    public void onBindViewHolder(TechnologyViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentTechnology();
                fragmentManager.beginTransaction().
                        replace(R.id.activity_second_content_container, fragment).commit();
            }
        });
                holder.fillWithTechnology(technologies.get(position));
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