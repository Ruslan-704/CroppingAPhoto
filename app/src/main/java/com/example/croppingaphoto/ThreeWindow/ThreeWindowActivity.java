package com.example.croppingaphoto.ThreeWindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.croppingaphoto.R;

public class ThreeWindowActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_window);
        imageView = findViewById(R.id.imageThreeWindow);

        Intent listActivity = getIntent();
        String name = listActivity.getStringExtra("nameFile");
        Bitmap bitmap = BitmapFactory.decodeFile(name);

        imageView.setImageBitmap(bitmap);
    }
}
