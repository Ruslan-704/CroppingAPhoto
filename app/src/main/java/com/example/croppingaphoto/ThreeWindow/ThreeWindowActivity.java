package com.example.croppingaphoto.ThreeWindow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.croppingaphoto.OneWindow.TheFirstWindowViewActivity;
import com.example.croppingaphoto.R;

public class ThreeWindowActivity extends AppCompatActivity {

    ImageView imageView;

    @SuppressLint("NewApi")
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_button:
                Intent firstWindowIntent = new Intent(ThreeWindowActivity.this,TheFirstWindowViewActivity.class);
                startActivity(firstWindowIntent);
                break;
        }
        return true;
    }

}

