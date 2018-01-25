package com.example.noureldeen.movieguide;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.noureldeen.movieguide.contracts.Components;
import com.example.noureldeen.movieguide.contracts.DaggerComponents;
import com.example.noureldeen.movieguide.models.Retrofit;
import com.example.noureldeen.movieguide.modules.RetrofitModule;
import com.example.noureldeen.movieguide.modules.SearchTypeModule;

/**
 * Created by noureldeen on 1/20/2018.
 */

public class MyApplication extends Application {
    private Components components;
    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        components = DaggerComponents.builder().retrofitModule(new RetrofitModule()).searchTypeModule(new SearchTypeModule(this)).build();
        retrofit =components.providesRetrofit();
        sharedPreferences = components.provideSharedPreference();
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
    public SharedPreferences getSharedPreference(){
        return sharedPreferences;
    }
}
