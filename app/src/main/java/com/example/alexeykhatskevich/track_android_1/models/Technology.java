package com.example.alexeykhatskevich.track_android_1.models;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.LruCache;

import com.example.alexeykhatskevich.track_android_1.server.ServerApi;

import org.json.JSONException;
import org.json.JSONObject;

public class Technology {

    private static LruCache<String,Bitmap> sharedImageCache = new LruCache<>(1024*1000);

    public static String ARG_TECHNOLOGY = "technology";
    private static String ARG_PICTURE_URL = "picture";
    private static String ARG_TITLE = "title";
    private static String ARG_INFO = "info";

    private String pictureUrl;
    public String title;

    public String getInfo() {
        return info;
    }

    private String info;

    public Technology(JSONObject jsonData){
        try {
            this.pictureUrl = jsonData.getString(ARG_PICTURE_URL);
            this.title = jsonData.getString(ARG_TITLE);
            this.info = jsonData.getString(ARG_INFO);
        }catch (JSONException e){
        }
    }

    @Override
    public String toString() {
        String result = ARG_TECHNOLOGY + ": {";
        result = result + " " + ARG_PICTURE_URL + ":" + pictureUrl;
        result = result + " " + ARG_TITLE + ":" + title;
        result = result + " " + ARG_INFO + ":" + info +"}";
        return result;
    }

    public Bitmap getImageFromCache(){
        return sharedImageCache.get(this.pictureUrl);
    }
    public void putToCache(Bitmap image){
        sharedImageCache.put(this.pictureUrl, image);
    }
    public Bitmap getImage(){
        Bitmap image = getImageFromCache();
        if (null != image){
            return image;
        }
        image = ServerApi.getImageByUrlString(this.pictureUrl);
        this.putToCache(image);
        return image;
    }
}
