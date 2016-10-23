package com.vanhackathon.dreamshop.bean;

import java.util.List;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class Dream {

    private int id;
    private String category;
    private String subcategory;
    private List<Layer> Layers;
    private User User;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public List<Layer> getLayers() {
        return Layers;
    }

    public void setLayers(List<Layer> layers) {
        this.Layers = layers;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        this.User = user;
    }


    public Dream(int id, String category, String subcategory, List<Layer> layers, User user) {
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
        this.Layers = layers;
        this.User = user;
    }

    public Dream() {
    }
}
