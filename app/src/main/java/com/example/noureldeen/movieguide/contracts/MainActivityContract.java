package com.example.noureldeen.movieguide.contracts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.models.Movie;

import java.util.List;

/**
 * Created by noureldeen on 1/20/2018.
 */

public interface MainActivityContract {
    interface MainActivityView{
        void publishMovies(List<Movie> movies);
        void showWaitingIndicator();
        void hideWaitingIndicator();
        MyApplication getMyApplication();
    }
    interface MainActivityModel{
        SharedPreferences getSharedPreference(MyApplication myApplication);
        void getPopularMovies(MyApplication myApplication, final MovieDBCallsListener movieDBCallsListener);
        void getTopRatedMovies(MyApplication myApplication,final MovieDBCallsListener movieDBCallsListener);
        void getFavoriteMovies(MyApplication myApplication,final MovieDBCallsListener movieDBCallsListener);
    }
    interface MainActivityPresenter{
        void attachDataToRecyclerView();
        void posterImageClicked(Context context, Bundle bundle);
        void popularItemSelected();
        void topratedItemSelected();
        void favoriteItemSelected();
    }
}
