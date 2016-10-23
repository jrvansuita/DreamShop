package com.vanhackathon.dreamshop.shopify;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jrvansuita place 22/10/16.
 */

public class ShopifyServiceGenerator {

    private static final String API_BASE_URL = "https://dreamandshop.myshopify.com";
    private static final String USER = "4051e9aef3488a6e2a3255750aafe230";
    private static final String PASS = "75bf5d789a468517b53965ef7ac8b1d9";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static Retrofit build() {
            String credentials = USER + ":" + PASS;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Accept", "application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

        OkHttpClient client = httpClient.build();
        return builder.client(client).build();
    }
}