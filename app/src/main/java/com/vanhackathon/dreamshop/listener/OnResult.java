package com.vanhackathon.dreamshop.listener;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jrvansuita on 22/10/16.
 */

public abstract class OnResult<T> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onResult(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    public abstract void onResult(T result);
}
