package com.example.musem.model;

public class Itinerary {

    private int id; // ID of the itinerary (auto-generated in the database)
    private String name; // Name of the museum or itinerary
    private String location; // Location of the museum
    private String time; // Time for the visit
    private String date; // Date for the visit
    private int imageResource; // Image resource ID for the museum image

    // Default constructor
    public Itinerary() {}

    // Constructor with all fields (except ID)
    public Itinerary(String name, String location, String time, String date, int imageResource) {
        this.name = name;
        this.location = location;
        this.time = time;
        this.date = date;
        this.imageResource = imageResource;
    }

    // Constructor including the ID (for when retrieving from the database)
    public Itinerary(int id, String name, String location, String time, String date, int imageResource) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.time = time;
        this.date = date;
        this.imageResource = imageResource;
    }

    // Getters and setters for each field

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    // Optional: Override toString() for easy debugging or logging
    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", imageResource=" + imageResource +
                '}';
    }
}
