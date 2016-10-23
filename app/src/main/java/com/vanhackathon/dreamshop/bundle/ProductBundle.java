package com.vanhackathon.dreamshop.bundle;

import com.vanhackathon.dreamshop.bean.Product;

import java.util.List;

/**
 * Created by jrvansuita place 22/10/16.
 */

public class ProductBundle {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProductBundle(List<Product> products) {
        this.products = products;
    }
}
