package com.example.alexeykhatskevich.track_android_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    AsyncTask second_activity_start_task=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        second_activity_start_task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
                finish();
            }


        };
        second_activity_start_task.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(second_activity_start_task != null)
            if (!second_activity_start_task.isCancelled())
                second_activity_start_task.cancel(false);
    }
}
