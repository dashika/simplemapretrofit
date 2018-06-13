package com.example.admin.simplemapretrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppSimpleMapRetrofit extends Application {

    private static AppSimpleMapRetrofit instance;

    public static AppSimpleMapRetrofit get() {
        return instance;
    }

    public static final String apiBaseUrl = "http://www.json-generator.com/";

    private static Retrofit builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(apiBaseUrl).build();

    static <S> S createService(Class<S> serviceClass) {
        return builder.create(serviceClass);
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
