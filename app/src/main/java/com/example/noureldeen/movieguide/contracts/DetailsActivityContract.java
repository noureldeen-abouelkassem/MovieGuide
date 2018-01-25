package com.example.noureldeen.movieguide.contracts;

import android.content.Intent;

import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.models.MovieReview;
import com.example.noureldeen.movieguide.models.MovieVideo;

import java.net.URL;
import java.util.List;

/**
 * Created by noureldeen on 1/20/2018.
 */

public interface DetailsActivityContract {
    interface DetailsActivityView {
        void bindDataToViews(String MovieTitle,
                      int MovieID,
                      URL MoviePosterThumbnail,
                      String MovieOverView,
                      float MovieVoteAverage,
                      String MovieReleaseDate);
        void publishMovieReviews(List<MovieReview> movieReviews);
        void publishMovieVideos(List<MovieVideo> movieVideos);
        Intent getMovieIntent();
        MyApplication getMyApplication();
        void favoriteButtonDisable();
        void favoriteButtonEnable();
    }

    interface DetailsActivityModel {
        void getMovieReviews(MyApplication myApplication, final MovieDBCallsListener movieDBCallsListener);
        void getMovieVideos(MyApplication myApplication, final MovieDBCallsListener movieDBCallsListener);
        void addMovieToFavorites(MyApplication myApplication);
        boolean isAFavoriteMovie(MyApplication myApplication);
        void deleteMovieFromFavorites(MyApplication myApplication);
    }

    interface DetailsActivityPresenter {
        void attachDataToRecyclerViews();
        void movieVideoItemClicked(final int position);
        void favoriteButtonClicked(MyApplication myApplication);
    }
}
