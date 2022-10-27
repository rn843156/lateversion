package com.example.myapplication;

public class Task {
    private String Title;
    private int imageID;
    public Task(String Title){
        this.Title = Title;
        //this.imageID = imageID;

    }

    public String getTitle(){
        return Title;

    }
    public int getImageID(){
        return  imageID;
    }
}
