package com.example.noureldeen.movieguide.models;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by noureldeen on 1/20/2018.
 */

public class Retrofit {
    private retrofit2.Retrofit retrofit = null;
    public retrofit2.Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder().baseUrl("https://api.themoviedb.org").addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
            return  retrofit;
        }
        return retrofit;
    }
}
