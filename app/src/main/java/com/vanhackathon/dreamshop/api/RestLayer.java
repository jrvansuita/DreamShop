package com.vanhackathon.dreamshop.api;

import com.vanhackathon.dreamshop.bean.Layer;
import com.vanhackathon.dreamshop.listener.OnResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jrvansuita place 21/10/16.
 */

public class RestLayer extends Rest {

    public static RestLayer get() {
        return new RestLayer();
    }

    public RestLayer getAllOfDream(int idDream,  OnResult<List<Layer>> onResult) {
        Rest.build().create(IDream.class).getAllOfDream(idDream).enqueue(onResult);
        return this;
    }

    public RestLayer getOne(int id, OnResult<Layer> onResult) {
        Rest.build().create(IDream.class).getOne(id).enqueue(onResult);
        return this;
    }

    public RestLayer newLayer(Layer layer, OnResult<Layer> onResult) {
        Rest.build().create(IDream.class).newLayer(layer).enqueue(onResult);
        return this;
    }

    public interface IDream {
        @GET("/api/layers/dream/{id}")
        Call<List<Layer>> getAllOfDream(@Path("id") int idDream);

        @GET("/api/layers/{id}")
        Call<Layer> getOne(@Path("id") int id);

        @POST("/api/layers")
        Call<Layer> newLayer(@Body Layer layer);
    }
}
