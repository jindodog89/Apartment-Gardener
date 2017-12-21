package com.example.choi.apartmentgardener;

/**
 * Created by choi on 11/30/17.
 */

public class User {
    private String username = "";
    private String act_type = "";
    private String email = "";

    public User(String username, String act_type, String email){
        this.username = username;
        this.act_type = act_type;
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return email;
    }

    public boolean isLandowner(){
        return (act_type.equals("landowner"));
    }

    public boolean isGardener(){
        return (act_type.equals("gardener"));
    }

    public String getAct_type(){
        return act_type;
    }
}