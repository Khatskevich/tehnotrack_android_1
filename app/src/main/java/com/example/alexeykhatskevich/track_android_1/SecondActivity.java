package com.example.alexeykhatskevich.track_android_1;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (savedInstanceState == null) {
            Fragment technologiesFragment = new FragmentTechnologyRecycler();
            getSupportFragmentManager().beginTransaction().add(R.id.activity_second_content_container, technologiesFragment).commit();
        }

    }
}