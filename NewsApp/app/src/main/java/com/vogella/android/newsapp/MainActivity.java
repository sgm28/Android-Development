package com.vogella.android.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Bitmap;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    RecyclerView rvNews;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        rvNews = (RecyclerView) findViewById(R.id.rvNews);
        newsAdapter = new NewsAdapter(new ArrayList<News>());
        rvNews.setAdapter(newsAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(0,null, this);


    }


    //Loader Stuff
    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,"https://content.guardianapis.com/search?show-fields=thumbnail&api-key=test");
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
            //Update the UI

             newsAdapter.setData(data);
             newsAdapter.notifyDataSetChanged();




    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        //Clean up
    }
}