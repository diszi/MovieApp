package com.movieapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String ROOT_URL = "https://api.themoviedb.org/";



    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static APIService getApiService() {
        System.out.println(" getApiService");
        return getRetrofitInstance().create(APIService.class);
    }


}
