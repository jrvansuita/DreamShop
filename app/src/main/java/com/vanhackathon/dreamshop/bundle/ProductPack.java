package com.vanhackathon.dreamshop.bundle;

import com.vanhackathon.dreamshop.bean.Product;

/**
 * Created by jrvansuita on 22/10/16.
 */

public class ProductPack {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductPack(Product product) {
        this.product = product;
    }
}
