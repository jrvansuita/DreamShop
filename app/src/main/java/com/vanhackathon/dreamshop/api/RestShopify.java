package com.vanhackathon.dreamshop.api;

import com.vanhackathon.dreamshop.bundle.ProductBundle;
import com.vanhackathon.dreamshop.bundle.ProductPack;
import com.vanhackathon.dreamshop.listener.OnResult;
import com.vanhackathon.dreamshop.shopify.ShopifyServiceGenerator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jrvansuita place 22/10/16.
 */

public class RestShopify {

    /*


    */
    public static RestShopify build() {
        return new RestShopify();
    }

    public void requestProducts(OnResult<ProductBundle> onGetAllProducts) {
        ShopifyServiceGenerator.build().create(IShopifyRest.class).getAllProducts().enqueue(onGetAllProducts);
    }

    public void requestProduct(String id, OnResult<ProductPack> onResult) {
        ShopifyServiceGenerator.build().create(IShopifyRest.class).getSingle(id).enqueue(onResult);
    }

    public interface IShopifyRest {
        @GET("/admin/products.json")
        Call<ProductBundle> getAllProducts();

        @GET("/admin/products/{id}.json")
        Call<ProductPack> getSingle(@Path("id") String id);
    }


}
