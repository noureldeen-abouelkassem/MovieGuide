package com.example.noureldeen.movieguide.modules;

import com.example.noureldeen.movieguide.models.Retrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noureldeen on 1/19/2018.
 */
@Module
public class RetrofitModule {
    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit();
    }
}
