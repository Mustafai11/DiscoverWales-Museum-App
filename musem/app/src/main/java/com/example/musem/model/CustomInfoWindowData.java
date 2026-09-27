package com.example.musem.model;


import androidx.annotation.NonNull;

public class CustomInfoWindowData {

    private String title;
    private String desc;
    private int image;

    // Constructor
    public CustomInfoWindowData(String title, String desc, int image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getImage() {
        return image;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(int image) {
        this.image = image;
    }

    // Optional: Override toString for easier debugging or printing
    @NonNull
    @Override
    public String toString() {
        return "CustomInfoWindowData{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", image=" + image +
                '}';
    }
}
