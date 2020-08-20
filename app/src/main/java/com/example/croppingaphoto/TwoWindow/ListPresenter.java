package com.example.croppingaphoto.TwoWindow;



import android.graphics.Bitmap;
import android.net.Uri;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListPresenter implements ListPresInterface {

   private ListActivityInterface listActivity;
   private List<ImagesList> imagesLists;
   private String jsonList;

     ListPresenter(ListActivityInterface listActivity){
        this.listActivity = listActivity;
    }

    @Override
    public void init(){
        imagesLists = new ArrayList<>();
    }

    @Override
    public List<ImagesList> getImagesLists() {
        return imagesLists;
    }

    @Override
    public void getIntent()  {

        Uri uri = listActivity.getIntentUri();
        Bitmap bitmap = null;
        bitmap = listActivity.getBitmap();
        String ur = String.valueOf(uri);

        if(imagesLists.size()!=0) {
            String checkingTheTransitionFromWindow3 = String.valueOf(imagesLists.get(0).getUri());
            if (!checkingTheTransitionFromWindow3.equals(ur)) {
                bitmapToFile(bitmap, ur);
            }
        }
        else
            bitmapToFile(bitmap, ur);

    }

    @Override
    public String getJsonList(){
        String jsonList = new Gson().toJson(imagesLists);
        return jsonList;
    }
    @Override
    public void deserializationJson(){
        Type type = new TypeToken<List<ImagesList>>() {
        }.getType();
         imagesLists = new Gson().fromJson(jsonList, type);
    }
    @Override
    public void setJsonList(String jsonList){
        this.jsonList = jsonList;
    }

    @Override
    public  void bitmapToFile( Bitmap bitmap, String uri) {

        File f = new File(listActivity.getCahdir(), "/save +" + System.currentTimeMillis());
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0,bos) ;
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagesLists.add(0,new ImagesList(f.getPath(),uri));
    }
}

