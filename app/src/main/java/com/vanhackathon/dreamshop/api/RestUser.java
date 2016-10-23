package com.vanhackathon.dreamshop.api;

import com.vanhackathon.dreamshop.bean.User;
import com.vanhackathon.dreamshop.listener.OnResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by jrvansuita place 21/10/16.
 */

public class RestUser extends  Rest {


    public RestUser newUser(User user) {
        INewUser s = Rest.build().create(INewUser.class);

        Call<User> call = s.newUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                    if (response.body() == null || response.code() != 200) {
                        this.onFailure(call, new Throwable(response.message()));
                    } else {
                        if (onSucess != null)
                            onSucess.onSucess(response.body().toString());
                    }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (onError != null)
                    onError.onError(t.getMessage());
            }
        });

        return this;
    }

    public RestUser getOne(OnResult<User> onResult) {
        Rest.build().create(INewUser.class).getOne().enqueue(onResult);
        return this;
    }


    public static RestUser get() {
        return new RestUser();
    }

    public interface INewUser {
        @POST("/user")
        Call<User> newUser(@Body User user);

        @GET("/user")
        Call<User> getOne();
    }


    public interface OnSucess {
        void onSucess(String s);
    }

    public interface OnError {
        void onError(String s);
    }


    protected OnSucess onSucess;
    protected OnError onError;

    public RestUser setOnSucess(OnSucess onSucess) {
        this.onSucess = onSucess;
        return this;
    }

    public RestUser setOnError(OnError onError) {
        this.onError = onError;
        return this;
    }
}
