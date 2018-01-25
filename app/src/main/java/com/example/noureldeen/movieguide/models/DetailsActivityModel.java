package com.example.noureldeen.movieguide.models;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;

import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.contracts.DetailsActivityContract;
import com.example.noureldeen.movieguide.contracts.MovieDBCalls;
import com.example.noureldeen.movieguide.contracts.MovieDBCallsListener;

import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by noureldeen on 1/21/2018.
 */

public class DetailsActivityModel implements DetailsActivityContract.DetailsActivityModel {
    private Retrofit retrofit;
    private String MovieTitle;
    private int MovieID ;
    private URL MoviePosterThumbnail ;
    private String MovieOverView ;
    private float MovieVoteAverage ;
    private String MovieReleaseDate ;
    public DetailsActivityModel(Intent intent) {
        if (intent.hasExtra("movieID")) {
            MovieID = intent.getIntExtra("movieID", 0);
        }
        if (intent.hasExtra("movieTitle")) {
            MovieTitle = intent.getStringExtra("movieTitle");
        }
        if (intent.hasExtra("movieThumbnail")) {
            try {
                MoviePosterThumbnail = new URL(intent.getStringExtra("movieThumbnail"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (intent.hasExtra("movieRate")) {
            MovieVoteAverage = intent.getFloatExtra("movieRate", 0);
        }
        if (intent.hasExtra("movieReleaseDate")) {
            MovieReleaseDate = intent.getStringExtra("movieReleaseDate");
        }
        if (intent.hasExtra("movieOverView")) {
            MovieOverView = intent.getStringExtra("movieOverView");
        }
    }

    public String getMovieTitle() {
        return MovieTitle;
    }

    public int getMovieID() {
        return MovieID;
    }

    public URL getMoviePosterThumbnail() {
        return MoviePosterThumbnail;
    }

    public String getMovieOverView() {
        return MovieOverView;
    }

    public float getMovieVoteAverage() {
        return MovieVoteAverage;
    }

    public String getMovieReleaseDate() {
        return MovieReleaseDate;
    }
    @Override
    public void getMovieReviews(MyApplication myApplication, final MovieDBCallsListener movieDBCallsListener) {
        retrofit = myApplication.getRetrofit();
        MovieDBCalls movieDBCalls = retrofit.getRetrofit().create(MovieDBCalls.class);
        io.reactivex.Observable<ListOfMovieReviews> listOfMoviesObservable = movieDBCalls.getMovieReviews(MovieID,"API_KEY")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        listOfMoviesObservable.subscribe(new Observer<ListOfMovieReviews>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ListOfMovieReviews listOfMovieReviews) {
                movieDBCallsListener.onGetMovieReviews(listOfMovieReviews.getResults());
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
    public void getMovieVideos(MyApplication myApplication, final MovieDBCallsListener movieDBCallsListener) {
        retrofit = myApplication.getRetrofit();
        MovieDBCalls movieDBCalls = retrofit.getRetrofit().create(MovieDBCalls.class);
        io.reactivex.Observable<ListOfMovieVideos> listOfMoviesObservable = movieDBCalls.getMovieVideos(MovieID,"API_KEY")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        listOfMoviesObservable.subscribe(new Observer<ListOfMovieVideos>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ListOfMovieVideos listOfMovieVideos) {
                movieDBCallsListener.onGetMovieVideos(listOfMovieVideos.getResults());
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
    public void addMovieToFavorites(MyApplication myApplication){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDBContract.MovieItem.MOVIE_ID,MovieID);
        contentValues.put(MovieDBContract.MovieItem.MOVIE_TITLE,MovieTitle);
        myApplication.getContentResolver().insert(MovieDBContract.MovieItem.TABLE_CONTENT_URI,contentValues);
    }
    @Override
    public boolean isAFavoriteMovie(MyApplication myApplication){
        boolean isFavorite = false;
        String selection = MovieDBContract.MovieItem.MOVIE_ID+" = ? ";
        String[] selectionArgs = new String[]{String.valueOf(MovieID)};
        Cursor cursor = myApplication.getContentResolver().query(MovieDBContract.MovieItem.TABLE_CONTENT_URI,null, selection ,selectionArgs,null,null);
        if( cursor != null && cursor.getCount() == 0){
            isFavorite = false;
            cursor.close();
        } else if (cursor != null){
            isFavorite = true;
            cursor.close();
        }
        return isFavorite;
    }
    @Override
    public void deleteMovieFromFavorites(MyApplication myApplication){
        String selection = MovieDBContract.MovieItem.MOVIE_ID+" = ? ";
        String[] selectionArgs = new String[]{String.valueOf(MovieID)};
        myApplication.getContentResolver().delete(MovieDBContract.MovieItem.TABLE_CONTENT_URI,selection,selectionArgs);
    }
}
