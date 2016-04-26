package com.example.alexeykhatskevich.track_android_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alexeykhatskevich.track_android_1.models.Technology;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class FirstActivity extends AppCompatActivity {

    private volatile boolean downloadedForContinue = false;
    private volatile boolean waitEnoughForContinue = false;
    AsyncTask second_activity_start_task=null;
    AsyncTask loadTask=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        second_activity_start_task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                waitEnoughForContinue = true;
                tryToOpenNextScreen();
            }
        };

        loadTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                TechnologyRecyclerAdapter.renewData();
               return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                downloadedForContinue = true;
                tryToOpenNextScreen();
            }
        };
        second_activity_start_task.execute();
        loadTask.execute();
    }

    private synchronized void tryToOpenNextScreen(){
        if (downloadedForContinue != true || waitEnoughForContinue != true){
            return;
        }
        cancelTasks();
        startActivity(new Intent(FirstActivity.this, SecondActivity.class));
        finish();
    }

    private void cancelTasks(){
        if(second_activity_start_task != null)
            if (!second_activity_start_task.isCancelled())
                second_activity_start_task.cancel(false);
        if(loadTask != null)
            if (!loadTask.isCancelled())
                loadTask.cancel(false);
    }

    @Override
    protected void onDestroy() {
        cancelTasks();
        super.onDestroy();
    }
}
