package com.example.musem.model;

public class Museum {

    // Fields (variables)
    private int id;
    private String name;
    private int image;
    private String exhibits;
    private int founded;
    private String location;
    private double latitude;
    private double longitude;

    // Constructor to initialize the model
    public Museum(int id, String name, int image, String exhibits, int founded, String location, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.exhibits = exhibits;
        this.founded = founded;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getExhibits() {
        return exhibits;
    }

    public void setExhibits(String exhibits) {
        this.exhibits = exhibits;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Optional: toString() method for easy printing of object details
    @Override
    public String toString() {
        return "Museum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", exhibits='" + exhibits + '\'' +
                ", founded=" + founded +
                ", location='" + location + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
