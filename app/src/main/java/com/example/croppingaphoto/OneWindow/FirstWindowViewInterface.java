package com.example.croppingaphoto.OneWindow;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

public interface FirstWindowViewInterface {

     void showDialog(final Uri imageUri);
     void startCrop(Uri imageUri);
     File getStorageDir();
     void startIntent(Intent intent, int code);
     void intentToListWindow(Uri uri);
     PackageManager getPackageManagerr();
     Uri getUri(File file);
}
