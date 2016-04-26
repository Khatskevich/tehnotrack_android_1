package com.example.alexeykhatskevich.track_android_1;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentTechnologyRecycler extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_technologies_recycler, container, false);
        setUpRecyclerView(containerView);
        return containerView;
    }

    void setUpRecyclerView(View container) {
        Activity activity = getActivity();
        if (activity != null) {
            RecyclerView mRecyclerView = (RecyclerView) container.findViewById(R.id.fragment_technologies_recycler_view);
            mRecyclerView.setAdapter(
                    new TechnologyRecyclerAdapter(activity.getLayoutInflater(),
                    activity.getApplicationContext(),
                    this.getActivity().getSupportFragmentManager()));
            mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            mRecyclerView.setHasFixedSize(true);
        }
    }

}
