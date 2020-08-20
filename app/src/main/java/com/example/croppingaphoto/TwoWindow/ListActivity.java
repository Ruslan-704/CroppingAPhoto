package com.example.croppingaphoto.TwoWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;

import com.example.croppingaphoto.R;

import java.io.File;
import java.io.IOException;

public class ListActivity extends AppCompatActivity  implements ListActivityInterface{

    private ViewPager2 viewPager2;
    ListPresInterface listPresenter;
    SharedPreferences myPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listPresenter = new ListPresenter(this);
        viewPager2 = findViewById(R.id.vp2_pagerList);
        listPresenter.init();


        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //myPreferences.edit().clear().commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
            SharedPreferences.Editor editor = myPreferences.edit();
            editor.putString("JsonList", listPresenter.getJsonList());
            editor.apply();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (myPreferences.contains("JsonList")) {
            listPresenter.setJsonList(myPreferences.getString("JsonList",null));
            listPresenter.deserializationJson();
        }
        listPresenter.getIntent();
        createVp(viewPager2);
    }

    @Override
    public void createVp(ViewPager2 viewPager2) {
        viewPager2.setAdapter(new VpAdapter(listPresenter.getImagesLists(), this));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
    }

    @Override
    public Uri getIntentUri(){
        Intent oneWindowIntent = this.getIntent();
        Uri uri = oneWindowIntent.getData();
        return uri;
    }

    @Override
    public Bitmap getBitmap(){
        try {
            return   MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), getIntentUri());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public File getCahdir(){
        return getCacheDir();
    }
}
