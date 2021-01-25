package com.vogella.android.newsapp;

import android.widget.ImageView;

public class News {



    private String title;
    private String section;
    private String author;
    private String date;
    private ImageView image;

    /**
     * @param title the tile of the news article
     * @param section the section such as potics, technology, etc
     * @param author The author of the tile
     * @param date The date of the news article
     * @param image The url of the image
     */

    public News(String title, String section, String author, String date, ImageView image)
    {
        this.title = title;
        this.section = section;
        this.author = author;
        this.date   = date;
        this.image = image;

    }

    /**
     * @return tile
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return return section category
     */
    public String getSection() {
        return section;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return ImageView
     */
    public ImageView getImage() {
        return image;
    }

    /**
     * @return Date
     */
    public String getDate() {
        return date;
    }


}
