package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

public class Listing {
    private String title;
    private String user;
    private String location;
    private String description;
    private int id;

    public Listing(String user, String location, String title, String description, int id){
        this.user = user;
        this.location = location;
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public String getUser() {
        return user;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getId(){return id;}

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}