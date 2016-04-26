package com.example.alexeykhatskevich.track_android_1;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexeykhatskevich.track_android_1.models.Technology;

public class FragmentTechnology extends Fragment {
    public int position = 0;
    private TextView mText;
    private AsyncTask imageDownloadTask;
    private ImageView imageView;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_technology_view, container, false);

        mText = (TextView)rootView.findViewById(R.id.textView);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);
        final Technology technology = TechnologyRecyclerAdapter.technologies.get(position);
        mText.setText(technology.getInfo());
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
        return rootView;
    }
    public static FragmentTechnology newInstance(int position) {
        FragmentTechnology technologyDetailFragment = new FragmentTechnology();
        technologyDetailFragment.position = position;
        return technologyDetailFragment;

    }

}
