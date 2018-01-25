package com.example.noureldeen.movieguide.contracts;

import com.example.noureldeen.movieguide.models.Movie;
import com.example.noureldeen.movieguide.models.MovieReview;
import com.example.noureldeen.movieguide.models.MovieVideo;

import java.util.List;

/**
 * Created by noureldeen on 1/20/2018.
 */

public interface MovieDBCallsListener {
    void onGetMovies (List<Movie> movieList);
    void onGetMovieReviews(List<MovieReview> movieReviews);
    void onGetMovieVideos(List<MovieVideo> movieVideos);
    void onGetAMovie(Movie movie,boolean endOfResult);
    void onFailure (Throwable e);
}
