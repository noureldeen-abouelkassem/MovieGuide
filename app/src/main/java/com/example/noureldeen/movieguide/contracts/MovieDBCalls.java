package com.example.noureldeen.movieguide.contracts;

import com.example.noureldeen.movieguide.models.ListOfMovieReviews;
import com.example.noureldeen.movieguide.models.ListOfMovieVideos;
import com.example.noureldeen.movieguide.models.ListOfMovies;
import com.example.noureldeen.movieguide.models.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by noureldeen on 1/20/2018.
 */

public interface MovieDBCalls {
    @GET("/3/movie/popular")
    Observable<ListOfMovies> getPopularMovies(@Query("api_key") String API_KEY);
    @GET("/3/movie/top_rated")
    Observable<ListOfMovies> getTopRatedMovies(@Query("api_key") String API_KEY);
    @GET("/3/movie/{MovieID}/reviews")
    Observable<ListOfMovieReviews> getMovieReviews(@Path("MovieID") int MovieID,@Query("api_key") String API_KEY);
    @GET("/3/movie/{MovieID}/videos")
    Observable<ListOfMovieVideos> getMovieVideos(@Path("MovieID") int MovieID,@Query("api_key") String API_KEY);
    @GET("/3/movie/{MovieID}")
    Observable<Movie> getAMovie(@Path("MovieID") int MovieID,@Query("api_key") String API_KEY);
}
