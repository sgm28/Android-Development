package com.vogella.android.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    //Endpoint for News
    private static String NEWS_BASE_URL = "https://content.guardianapis.com/search?";

    // Query Parameter
    private static final String QUERY_PARAM = "q";
    private static final String SHOW_TAGS= "show-tags";
    private static final String SHOW_FIELDS= "show-fields";
    private static final String API_KEY="api-key";

    //thumbnail

    public static String getNewsData(String url)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String newsJSONString = null;

        try{
            //Building the URL
            Uri builtURI = Uri.parse(NEWS_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM,"")
                    .appendQueryParameter(SHOW_TAGS,"author")
                    .appendQueryParameter(SHOW_FIELDS, "thumbnail")
                    .appendQueryParameter(API_KEY,"test")
                    .build();

            //Convert to a URL
            URL requestURL = new URL(builtURI.toString());


            //Start Network connection
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            //Create a buffer reader
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //String builder to hold incoming data
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);

                builder.append("\n"); //Makes debugging easier

            }

            if (builder.length() == 0)
            {
                return null;
            }

            newsJSONString = builder.toString();


        } catch (MalformedURLException e) {
           Log.e(LOG_TAG, "Error building url");
        } catch (ProtocolException e) {
            Log.e(LOG_TAG, "Problem with setRequestMethod");
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with openConnection and connect");
        }

        finally {
            //Close the connection
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem closing reader");
                }
            }

            Log.d(LOG_TAG, newsJSONString);
            return newsJSONString;
        }
    }

    //Image data
    public static Bitmap getNewsImagePerArticle(String urlImage)
    {
        Bitmap bmp = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String newsJSONString = null;

        try{
            //Building the URL
            Uri builtURI = Uri.parse(urlImage).buildUpon().build();

            //Convert to a URL
            URL requestURL = new URL(builtURI.toString());


            //Start Network connection
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            bmp = BitmapFactory.decodeStream(inputStream);




        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error building url");
        } catch (ProtocolException e) {
            Log.e(LOG_TAG, "Problem with setRequestMethod");
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with openConnection and connect");
        }

        finally {
            //Close the connection
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem closing reader");
                }
            }

            Log.d(LOG_TAG, String.valueOf(bmp));
            return bmp;

        }
    }


}
