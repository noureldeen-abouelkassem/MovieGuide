package com.example.noureldeen.movieguide.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noureldeen on 1/20/2018.
 */
@Module
public class SearchTypeModule {
    private final MyApplication myApplication;

    public SearchTypeModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return myApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreference(Context context){
        return context.getSharedPreferences(context.getString(R.string.searchType),Context.MODE_PRIVATE);
    }

}
