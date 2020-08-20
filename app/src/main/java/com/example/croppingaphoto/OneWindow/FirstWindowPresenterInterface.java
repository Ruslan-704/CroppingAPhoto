package com.example.croppingaphoto.OneWindow;

import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

public interface FirstWindowPresenterInterface {
    Uri getImageUri1();
    void clickingOnAPaperClip(int numberButton);
    void dispatchTakePictureIntent(Intent intent);
    File createImageFile() throws IOException;
}
