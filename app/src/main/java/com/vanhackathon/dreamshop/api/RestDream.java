package com.vanhackathon.dreamshop.api;

import com.vanhackathon.dreamshop.bean.Dream;
import com.vanhackathon.dreamshop.listener.OnResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jrvansuita place 21/10/16.
 */

public class RestDream extends Rest {

    public static RestDream get() {
        return new RestDream();
    }

    public RestDream getFeedAll(OnResult<List<Dream>> onResult) {
        Rest.build().create(IDream.class).getFeedAll().enqueue(onResult);
        return this;
    }

    public RestDream getMyAll(OnResult<List<Dream>> onResult) {
        Rest.build().create(IDream.class).getMyAll().enqueue(onResult);
        return this;
    }

    public RestDream getOne(int id, OnResult<Dream> onResult) {
        Rest.build().create(IDream.class).getOne(id).enqueue(onResult);

        return this;
    }

    public RestDream newDream(Dream dream, OnResult<Dream> onResult) {
        Rest.build().create(IDream.class).newDream(dream).enqueue(onResult);
        return this;
    }


    public RestDream delete(int idDream) {
        Rest.build().create(IDream.class).delete(idDream).enqueue(new OnResult<Dream>() {
            @Override
            public void onResult(Dream result) {

            }
        });
        return this;
    }


    public interface IDream {
        @GET("/feed/dreams/")
        Call<List<Dream>> getFeedAll();

        @GET("/api/dreams")
        Call<List<Dream>> getMyAll();

        @GET("/api/dreams/{id}")
        Call<Dream> getOne(@Path("id") int id);


        @POST("/api/dreams")
        Call<Dream> newDream(@Body Dream dream);

        @DELETE("/api/dreams/{id}")
        Call<Dream> delete(@Path("id") int id);
    }


}
