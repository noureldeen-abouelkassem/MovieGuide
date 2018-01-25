package com.example.noureldeen.movieguide.presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.noureldeen.movieguide.R;
import com.example.noureldeen.movieguide.contracts.MainActivityContract;
import com.example.noureldeen.movieguide.contracts.MovieDBCallsListener;
import com.example.noureldeen.movieguide.models.MainActivityModel;
import com.example.noureldeen.movieguide.models.Movie;
import com.example.noureldeen.movieguide.models.MovieReview;
import com.example.noureldeen.movieguide.models.MovieVideo;
import com.example.noureldeen.movieguide.views.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noureldeen on 1/20/2018.
 */

public class MainActivityPresenter implements MainActivityContract.MainActivityPresenter {
    private final MainActivityContract.MainActivityView mainActivityView;
    private final MainActivityModel mainActivityModel;

    public MainActivityPresenter(MainActivityContract.MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        mainActivityModel = new MainActivityModel();

    }

    @Override
    public void attachDataToRecyclerView() {
        mainActivityView.showWaitingIndicator();
        SharedPreferences sharedPreferences = mainActivityModel.getSharedPreference(mainActivityView.getMyApplication());
        String searchType = sharedPreferences.getString(mainActivityView.getMyApplication().getString(R.string.searchType),"popular");
        switch (searchType) {
            case "popular":
                mainActivityModel.getPopularMovies(mainActivityView.getMyApplication(), new MovieDBCallsListener() {
                    @Override
                    public void onGetMovies(List<Movie> movieList) {
                        mainActivityView.hideWaitingIndicator();
                        mainActivityView.publishMovies(movieList);
                    }

                    @Override
                    public void onGetMovieReviews(List<MovieReview> movieReviews) {

                    }

                    @Override
                    public void onGetMovieVideos(List<MovieVideo> movieVideos) {

                    }

                    @Override
                    public void onGetAMovie(Movie movie, boolean b) {

                    }


                    @Override
                    public void onFailure(Throwable e) {
                        Log.v(MainActivityPresenter.class.getSimpleName(), e.getMessage());
                    }
                });
                break;
            case "top_rated":
                mainActivityModel.getTopRatedMovies(mainActivityView.getMyApplication(), new MovieDBCallsListener() {
                    @Override
                    public void onGetMovies(List<Movie> movieList) {
                        mainActivityView.hideWaitingIndicator();
                        mainActivityView.publishMovies(movieList);
                    }

                    @Override
                    public void onGetMovieReviews(List<MovieReview> movieReviews) {

                    }

                    @Override
                    public void onGetMovieVideos(List<MovieVideo> movieVideos) {

                    }

                    @Override
                    public void onGetAMovie(Movie movie, boolean b) {

                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.v(MainActivityPresenter.class.getSimpleName(), e.getMessage());
                    }

                });
                break;
            case "favorites":
                mainActivityModel.getFavoriteMovies(mainActivityView.getMyApplication(), new MovieDBCallsListener() {
                    final List<Movie> movies = new ArrayList<>();

                    @Override
                    public void onGetMovies(List<Movie> movieList) {

                    }

                    @Override
                    public void onGetMovieReviews(List<MovieReview> movieReviews) {

                    }

                    @Override
                    public void onGetMovieVideos(List<MovieVideo> movieVideos) {

                    }

                    @Override
                    public void onGetAMovie(Movie movie, boolean endOfResult) {
                        if (endOfResult) {
                            finishLoading();
                        } else {
                            movies.add(movie);
                        }
                    }

                    void finishLoading() {
                        mainActivityView.hideWaitingIndicator();
                        mainActivityView.publishMovies(movies);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.v(MainActivityPresenter.class.getSimpleName(), e.getMessage());
                    }

                });

                break;
        }
    }

    @Override
    public void posterImageClicked(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("movieID",bundle.getInt("movieID"));
        intent.putExtra("movieTitle", bundle.getString("movieTitle"));
        intent.putExtra("movieThumbnail", bundle.getString("movieThumbnail"));
        intent.putExtra("movieRate", bundle.getFloat("movieRate"));
        intent.putExtra("movieReleaseDate", bundle.getString("movieReleaseDate"));
        intent.putExtra("movieOverView", bundle.getString("movieOverView"));
        context.startActivity(intent);
    }

    @Override
    public void popularItemSelected() {
        SharedPreferences sharedPreferences = mainActivityModel.getSharedPreference(mainActivityView.getMyApplication());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mainActivityView.getMyApplication().getString(R.string.searchType),"popular");
        editor.apply();
        mainActivityView.showWaitingIndicator();
        mainActivityModel.getPopularMovies(mainActivityView.getMyApplication(), new MovieDBCallsListener() {
            @Override
            public void onGetMovies(List<Movie> movieList) {
                mainActivityView.hideWaitingIndicator();
                mainActivityView.publishMovies(movieList);
            }

            @Override
            public void onGetMovieReviews(List<MovieReview> movieReviews) {

            }

            @Override
            public void onGetMovieVideos(List<MovieVideo> movieVideos) {

            }

            @Override
            public void onGetAMovie(Movie movie,boolean b) {

            }

            @Override
            public void onFailure(Throwable e) {
                Log.v(MainActivityPresenter.class.getSimpleName(),e.getMessage());
            }
        });
    }

    @Override
    public void topratedItemSelected() {
        SharedPreferences sharedPreferences = mainActivityModel.getSharedPreference(mainActivityView.getMyApplication());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mainActivityView.getMyApplication().getString(R.string.searchType),"top_rated");
        editor.apply();
        mainActivityView.showWaitingIndicator();
        mainActivityModel.getTopRatedMovies(mainActivityView.getMyApplication(), new MovieDBCallsListener() {
            @Override
            public void onGetMovies(List<Movie> movieList) {
                mainActivityView.hideWaitingIndicator();
                mainActivityView.publishMovies(movieList);
            }

            @Override
            public void onGetMovieReviews(List<MovieReview> movieReviews) {

            }

            @Override
            public void onGetMovieVideos(List<MovieVideo> movieVideos) {

            }

            @Override
            public void onGetAMovie(Movie movie,boolean b) {

            }

            @Override
            public void onFailure(Throwable e) {
                Log.v(MainActivityPresenter.class.getSimpleName(),e.getMessage());
            }
        });
    }

    @Override
    public void favoriteItemSelected() {
        SharedPreferences sharedPreferences = mainActivityModel.getSharedPreference(mainActivityView.getMyApplication());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mainActivityView.getMyApplication().getString(R.string.searchType),"favorites");
        editor.apply();
        mainActivityView.showWaitingIndicator();
        mainActivityModel.getFavoriteMovies(mainActivityView.getMyApplication(), new MovieDBCallsListener() {
            final List<Movie> movies = new ArrayList<>();

            @Override
            public void onGetMovies(List<Movie> movieList) {

            }

            @Override
            public void onGetMovieReviews(List<MovieReview> movieReviews) {

            }

            @Override
            public void onGetMovieVideos(List<MovieVideo> movieVideos) {

            }

            @Override
            public void onGetAMovie(Movie movie, boolean endOfResult) {
                if (endOfResult) {
                    finishLoading();
                } else {
                    movies.add(movie);
                }
            }

            void finishLoading() {
                mainActivityView.hideWaitingIndicator();
                mainActivityView.publishMovies(movies);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.v(MainActivityPresenter.class.getSimpleName(), e.getMessage());
            }
        });
    }
}
