package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

public class LandOwnerInfo {
    private int id;
    private String title = "";
    private String description = "";
    private String location = "";

    public LandOwnerInfo(int newId, String title, String description, String location) {
        this.id = newId;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setId(int newId){
        this.id = newId;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setDescription(String newDescription) {
        this.title = newDescription;
    }

    public void setLocation(String newLocation){
        this.location = newLocation;
    }

}
