package com.vanhackathon.dreamshop.bean;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class Layer {

    private Integer id;
    private String type;
    private String description;
    private String url;
    private String product_id;
    private int dream_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProyductId(String productId) {
        this.product_id = productId;
    }


    public Layer(Integer id, String type, String description, String url, String productId) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.url = url;
        this.product_id = productId;
    }

    public int getDream_id() {
        return dream_id;
    }

    public void setDream_id(int dream_id) {
        this.dream_id = dream_id;
    }


    public Layer() {
    }
}
