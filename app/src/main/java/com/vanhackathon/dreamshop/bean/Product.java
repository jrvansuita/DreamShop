package com.vanhackathon.dreamshop.bean;

import java.util.List;

/**
 * Created by jrvansuita place 22/10/16.
 */

public class Product {
    private long id;
    private String title;
    private String vendor;
    private List<Variants> variants;
    private Image image;


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVendor() {
        return vendor;
    }

    public float getPrice() {
        if (variants == null || variants.isEmpty())
            return 0;

        return variants.get(0).getPrice();
    }

    public String getImageUrl() {
        if (image == null)
            return "";

        return image.getSrc();
    }


    private class Variants {
        private float price;

        public float getPrice() {
            return price;
        }
    }


    private class Image {
        private String src;

        public String getSrc() {
            return src;
        }
    }
}
