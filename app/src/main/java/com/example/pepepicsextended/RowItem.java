package com.example.pepepicsextended;

import java.lang.reflect.Constructor;

public class RowItem {
    public String title;
    public String imageID;

    public RowItem(String title, String imageID) {
        this.title = title;
        this.imageID = imageID;
    }

    public String getImageId() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }
}