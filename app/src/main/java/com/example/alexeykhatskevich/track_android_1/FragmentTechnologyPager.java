package com.example.alexeykhatskevich.track_android_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTechnologyPager extends Fragment {
    private ViewPager mPager;
    private FragmentViewPagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_technologies_recycler, container, false);
        setUpViewPager(contentView);
        return contentView;
    }

    public void setUpViewPager(View contentView) {
        mPager = (ViewPager) contentView.findViewById(R.id.pager);
        mPagerAdapter = new FragmentViewPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {
        public FragmentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentTechnology.newInstance(position);
        }

        @Override
        public int getCount() {
            return TechnologyRecyclerAdapter.technologies.size();
        }
    }

}
