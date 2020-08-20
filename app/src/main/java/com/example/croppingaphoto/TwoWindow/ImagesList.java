package com.example.croppingaphoto.TwoWindow;



public class ImagesList {

    private String nameFile;
    private String uri;

     ImagesList(String nameFile, String  uri)
    {
        this.nameFile = nameFile;
        this.uri = uri;
    }

    public String getNameFile() {
        return nameFile;
    }

    public String getUri() {
        return uri;
    }

}
