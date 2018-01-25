package com.example.noureldeen.movieguide.presenters;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.contracts.DetailsActivityContract;
import com.example.noureldeen.movieguide.contracts.MovieDBCallsListener;
import com.example.noureldeen.movieguide.models.DetailsActivityModel;
import com.example.noureldeen.movieguide.models.Movie;
import com.example.noureldeen.movieguide.models.MovieReview;
import com.example.noureldeen.movieguide.models.MovieVideo;

import java.util.List;

/**
 * Created by noureldeen on 1/20/2018.
 */

public class DetailsActivityPresenter implements DetailsActivityContract.DetailsActivityPresenter {
    private final DetailsActivityContract.DetailsActivityView detailsActivityView;
    private final DetailsActivityModel detailsActivityModel;

    public DetailsActivityPresenter(DetailsActivityContract.DetailsActivityView detailsActivityView) {
        this.detailsActivityView = detailsActivityView;
        detailsActivityModel = new DetailsActivityModel(this.detailsActivityView.getMovieIntent());
    }

    @Override
    public void attachDataToRecyclerViews() {

        detailsActivityView.bindDataToViews(detailsActivityModel.getMovieTitle(),detailsActivityModel.getMovieID(),detailsActivityModel.getMoviePosterThumbnail(),detailsActivityModel.getMovieOverView(),detailsActivityModel.getMovieVoteAverage(),detailsActivityModel.getMovieReleaseDate());
        detailsActivityModel.getMovieReviews(detailsActivityView.getMyApplication(), new MovieDBCallsListener() {
            @Override
            public void onGetMovies(List<Movie> movieList) {

            }

            @Override
            public void onGetMovieReviews(List<MovieReview> movieReviews) {
                detailsActivityView.publishMovieReviews(movieReviews);
            }

            @Override
            public void onGetMovieVideos(List<MovieVideo> movieVideos) {
            }

            @Override
            public void onGetAMovie(Movie movie,boolean b) {

            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
        detailsActivityModel.getMovieVideos(detailsActivityView.getMyApplication(), new MovieDBCallsListener() {
            @Override
            public void onGetMovies(List<Movie> movieList) {

            }

            @Override
            public void onGetMovieReviews(List<MovieReview> movieReviews) {

            }

            @Override
            public void onGetMovieVideos(List<MovieVideo> movieVideos) {
                detailsActivityView.publishMovieVideos(movieVideos);
            }

            @Override
            public void onGetAMovie(Movie movie,boolean b) {

            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
        if(detailsActivityModel.isAFavoriteMovie(detailsActivityView.getMyApplication())){
            detailsActivityView.favoriteButtonEnable();
        }else {
            detailsActivityView.favoriteButtonDisable();
        }
    }

    @Override
    public void movieVideoItemClicked(final int position) {
        detailsActivityModel.getMovieVideos(detailsActivityView.getMyApplication(), new MovieDBCallsListener() {
            @Override
            public void onGetMovies(List<Movie> movieList) {

            }

            @Override
            public void onGetMovieReviews(List<MovieReview> movieReviews) {

            }

            @Override
            public void onGetMovieVideos(List<MovieVideo> movieVideos) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v="+movieVideos.get(position).getKey()));
                detailsActivityView.getMyApplication().getApplicationContext().startActivity(intent);
            }

            @Override
            public void onGetAMovie(Movie movie,boolean b) {

            }

            @Override
            public void onFailure(Throwable e) {

            }
        });


    }

    @Override
    public void favoriteButtonClicked(MyApplication myApplication) {
        if(detailsActivityModel.isAFavoriteMovie(detailsActivityView.getMyApplication())) {
            detailsActivityModel.deleteMovieFromFavorites(detailsActivityView.getMyApplication());
            detailsActivityView.favoriteButtonDisable();
            Toast.makeText(myApplication.getBaseContext(),detailsActivityModel.getMovieTitle()+" now removed from your favorites :)",Toast.LENGTH_LONG).show();
        }else {
            detailsActivityModel.addMovieToFavorites(detailsActivityView.getMyApplication());
            detailsActivityView.favoriteButtonEnable();
            Toast.makeText(myApplication.getBaseContext(),detailsActivityModel.getMovieTitle()+" is now one of your favorites :)",Toast.LENGTH_LONG).show();
        }
    }
}
