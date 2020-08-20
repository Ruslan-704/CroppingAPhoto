package com.example.croppingaphoto.OneWindow;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstWindowPresenter implements FirstWindowPresenterInterface{

    private FirstWindowViewInterface theFirstWindowActivity;
    private Uri imageUri1;

    private final int PHOTO = 1;
    private final int GALLERY = 200;


    public FirstWindowPresenter(FirstWindowViewInterface theFirstWindowActivity)
    {
        this.theFirstWindowActivity = theFirstWindowActivity;
    }

    @Override
    public Uri getImageUri1() {
        return imageUri1;
    }

    @Override
    public void clickingOnAPaperClip(int numberButton){

        if(numberButton == 0) {
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            theFirstWindowActivity.startIntent(gallery , GALLERY);
        }

        if(numberButton == 1){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            dispatchTakePictureIntent(takePictureIntent);
            theFirstWindowActivity.startIntent(takePictureIntent, PHOTO);
        }
    }

    //Для взятия полноразмерного фото...Иногда не работает ((

    @Override
    public void dispatchTakePictureIntent(Intent intent) {
        if (intent.resolveActivity(theFirstWindowActivity.getPackageManagerr()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                imageUri1 = theFirstWindowActivity.getUri(photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri1);
            }
        }
    }

    @Override
    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                theFirstWindowActivity.getStorageDir()
        );
        return image;
    }


}
