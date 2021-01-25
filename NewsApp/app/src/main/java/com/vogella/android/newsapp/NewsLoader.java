package com.vogella.android.newsapp;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {


    //Fields
    String url;
    List<News> news;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }



    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }


    @Nullable
    @Override
    public List<News> loadInBackground() {

        String newsJSONData = NetworkUtils.getNewsData(this.url);
        news = QueryUtils.extractFeatureFromJson(newsJSONData);
        return news;
    }

    @Override
    protected void onReset() {
        super.onReset();
    }
}
