package com.example.croppingaphoto.OneWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.croppingaphoto.R;
import com.example.croppingaphoto.TwoWindow.ListActivity;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;


public class TheFirstWindowViewActivity extends AppCompatActivity implements FirstWindowViewInterface {

    ImageButton gallery;
    ImageButton camera;
    FirstWindowPresenterInterface firstWindowPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstWindowPresenter = new FirstWindowPresenter(this);

        gallery = findViewById(R.id.ib_gallery);
        camera = findViewById(R.id.ib_camera);
        View.OnClickListener oMyButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.ib_gallery:
                        firstWindowPresenter.clickingOnAPaperClip(0);
                        break;
                    case R.id.ib_camera:
                        firstWindowPresenter.clickingOnAPaperClip(1);
                        break;
                }
            }
        };

        gallery.setOnClickListener(oMyButton);
        camera.setOnClickListener(oMyButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

            if(requestCode == 1 && resultCode == RESULT_OK) {
                showDialog(firstWindowPresenter.getImageUri1());
            }

            if(requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                Uri uri = CropImage.getPickImageResultUri(this, imageReturnedIntent);

                if(!CropImage.isReadExternalStoragePermissionsRequired(this,uri)){
                    showDialog(uri);
                }
            }

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                 CropImage.ActivityResult result = CropImage.getActivityResult(imageReturnedIntent);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    intentToListWindow(resultUri);
                }
            }


    }

    @Override
    public void showDialog(final Uri imageUri){
        final Dialog dialog = new Dialog(TheFirstWindowViewActivity.this);
        dialog.setContentView(R.layout.dialog_window);
        Button yes = dialog.findViewById(R.id.bt_yes);
        Button no = dialog.findViewById(R.id.bt_no);

        View.OnClickListener dialogButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_yes:
                        dialog.dismiss();
                        startCrop(imageUri);
                        break;

                    case R.id.bt_no:
                        dialog.dismiss();
                        intentToListWindow(imageUri);
                        break;
                }
            }
        };

        yes.setOnClickListener(dialogButton);
        no.setOnClickListener(dialogButton);

        dialog.show();
    }

    @Override
    public void startCrop(Uri imageUri){
        CropImage.activity(imageUri)
                .start(TheFirstWindowViewActivity.this);
    }

    @Override
    public File getStorageDir(){
        File file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return  file;
    }

    @Override
    public void startIntent(Intent intent, int code){
        startActivityForResult(intent,code);
    }

    @Override
    public void intentToListWindow(Uri uri) {
        Intent twoWindow = new Intent(this, ListActivity.class);
        twoWindow.setData(uri);
        startActivity(twoWindow);
    }

    @Override
    public PackageManager getPackageManagerr(){
        return getPackageManager();
    }

    @Override
    public  Uri getUri(File file){
       return FileProvider.getUriForFile(this,
                "com.example.android.provider",
                file);
    }
}








