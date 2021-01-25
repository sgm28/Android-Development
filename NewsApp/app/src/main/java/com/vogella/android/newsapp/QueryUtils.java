package com.vogella.android.newsapp;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     *
     *
     *
     */

    public static List<News>  extractFeatureFromJson (String newsJSON)
    {
        if(TextUtils.isEmpty(newsJSON))
        {
            return null;
        }

        List<News> news = new ArrayList();

        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            // Get the JSONArray of data items.
            // The name of the Array is result.
            JSONObject itemsArray = baseJsonResponse.getJSONObject("response");
            JSONArray  result = itemsArray.getJSONArray("results");

            // Initialize iterator, and variables to store the data.

            int i = 0;
            String title = null;
            String section =null;
            StringBuilder author = null;
            String date = null;
            ImageView image = null;


            // Look for results in the items array, exiting when items are found.

            while(i < itemsArray.length());
            {
                JSONObject currentNewsData = result.getJSONObject(i);

               title = String.valueOf(currentNewsData.getJSONObject("webTitle"));
               section = String.valueOf(currentNewsData.getJSONObject("sectionName"));
               date = String.valueOf(currentNewsData.getJSONObject("webPublicationDate"));

              //Tags array contains and author and contributor
               JSONArray tagsArray = currentNewsData.optJSONArray("tags");

               int j = 0;
               author = new StringBuilder();
               //Sometimes articles have more than one author
               while (j < tagsArray.length())
               {
                   author.append(tagsArray.getJSONObject(i).getJSONObject("webTitle"));
               }

               //The fields object can the image of the current article.
               String urlImage = String.valueOf(currentNewsData.optJSONObject("fields"));
               //Downloading the image
               image.setImageBitmap(NetworkUtils.getNewsImagePerArticle(urlImage));


               //Creating the News object
                News currentNewsDate = new News(title,section,author.toString(),date,image);

                //Logging the information
                Log.d(LOG_TAG, currentNewsData.toString());
                news.add(currentNewsDate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Return the News Array.
        return news;
    }
}
