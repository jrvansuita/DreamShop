package com.vanhackathon.dreamshop.bean;

/**
 * Created by jrvansuita place 21/10/16.
 */

public class User {
    private String firebase_key;
    private String name;
    private String photo_url;

    public String getFirebase_key() {
        return firebase_key;
    }

    public void setFirebase_key(String firebase_key) {
        this.firebase_key = firebase_key;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public User(String firebase_key, String name, String photo_url) {
        this.firebase_key = firebase_key;
        this.name = name;
        this.photo_url = photo_url;
    }

    public User() {

    }

    public User(String firebase_key, String name) {
        this.firebase_key = firebase_key;
        this.name = name;
    }
}
