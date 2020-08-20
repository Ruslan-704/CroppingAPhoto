package com.example.croppingaphoto.TwoWindow;

import android.graphics.Bitmap;

import java.util.List;


public interface ListPresInterface {
    void bitmapToFile(Bitmap bitmap, String uri);
    void setJsonList(String jsonList);
    void deserializationJson();
    String getJsonList();
    void getIntent();
    void init();
    List<ImagesList> getImagesLists();
}
