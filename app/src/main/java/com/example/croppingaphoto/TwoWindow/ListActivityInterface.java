package com.example.croppingaphoto.TwoWindow;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.viewpager2.widget.ViewPager2;

import java.io.File;

public interface ListActivityInterface {
    void createVp(ViewPager2 viewPager2);
    Uri getIntentUri();
    Bitmap getBitmap();
    File getCahdir();
}
