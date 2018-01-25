package com.example.noureldeen.movieguide.models;

import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.contracts.MainActivityContract;
import com.example.noureldeen.movieguide.contracts.MovieDBCalls;
import com.example.noureldeen.movieguide.contracts.MovieDBCallsListener;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by noureldeen on 1/20/2018.
 */

public class MainActivityModel implements MainActivityContract.MainActivityModel {
    private Retrofit retrofit;
    private SharedPreferences searchType;

    @Override
    public SharedPreferences getSharedPreference(MyApplication myApplication) {
        searchType = myApplication.getSharedPreference();
        return searchType;
    }

    @Override
    public void getPopularMovies(MyApplication myApplication ,final MovieDBCallsListener movieDBCallsListener) {
        retrofit = myApplication.getRetrofit();
        MovieDBCalls movieDBCalls = retrofit.getRetrofit().create(MovieDBCalls.class);
        io.reactivex.Observable<ListOfMovies> listOfMoviesObservable = movieDBCalls.getPopularMovies("API_KEY")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        listOfMoviesObservable.subscribe(new Observer<ListOfMovies>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ListOfMovies listOfMovies) {
               movieDBCallsListener.onGetMovies(listOfMovies.getResults());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getTopRatedMovies(MyApplication myApplication, final MovieDBCallsListener movieDBCallsListener) {
        retrofit = myApplication.getRetrofit();
        MovieDBCalls movieDBCalls = retrofit.getRetrofit().create(MovieDBCalls.class);
        io.reactivex.Observable<ListOfMovies> listOfMoviesObservable = movieDBCalls.getTopRatedMovies("API_KEY")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        listOfMoviesObservable.subscribe(new Observer<ListOfMovies>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ListOfMovies listOfMovies) {
                movieDBCallsListener.onGetMovies(listOfMovies.getResults());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getFavoriteMovies(MyApplication myApplication, final MovieDBCallsListener movieDBCallsListener) {
        Cursor cursor = myApplication.getContentResolver().query(MovieDBContract.MovieItem.TABLE_CONTENT_URI,new String[]{MovieDBContract.MovieItem.MOVIE_ID},null,null,null);
        while(cursor != null &&cursor.moveToNext()){
            retrofit = myApplication.getRetrofit();
            final MovieDBCalls movieDBCalls = retrofit.getRetrofit().create(MovieDBCalls.class);
            int MovieID = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBContract.MovieItem.MOVIE_ID));
            io.reactivex.Observable<Movie> listOfMoviesObservable = movieDBCalls.getAMovie(MovieID,"API_KEY")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
            listOfMoviesObservable.subscribe(new Observer<Movie>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Movie movie) {
                    if(movie != null){
                        movieDBCallsListener.onGetAMovie(movie,false);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    movieDBCallsListener.onGetAMovie(null,true);
                }
            });
        }
    }
}
