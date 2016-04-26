package com.example.alexeykhatskevich.track_android_1.server;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alexeykhatskevich.track_android_1.models.Technology;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ServerApi {
    public static String baseUrl = "http://mobevo.ext.terrhq.ru/";
    public static ArrayList<Technology> getTechnologies(){
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
        try {
            url = new URL(baseUrl + "shr/j/ru/technology.js");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(15 * 1000);
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONObject technologiesJSON = jsonObject.getJSONObject("technology");
            Iterator<String> iter = technologiesJSON.keys();
            ArrayList<Technology>  technologies = new ArrayList<>();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    JSONObject value = (JSONObject) technologiesJSON.get(key);
                    technologies.add(new Technology(value));
                } catch (JSONException e) {
                }
            }
            return technologies;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Bitmap getImageByUrlString( String src){
        try {
            java.net.URL url = new java.net.URL(baseUrl + src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
