package com.example.noureldeen.movieguide.contracts;

import android.content.SharedPreferences;

import com.example.noureldeen.movieguide.models.Retrofit;
import com.example.noureldeen.movieguide.modules.RetrofitModule;
import com.example.noureldeen.movieguide.modules.SearchTypeModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {RetrofitModule.class, SearchTypeModule.class})
public interface Components {
    Retrofit providesRetrofit();
    SharedPreferences provideSharedPreference();
}
