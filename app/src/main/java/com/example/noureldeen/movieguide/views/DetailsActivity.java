package com.example.noureldeen.movieguide.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.R;
import com.example.noureldeen.movieguide.adapter.MovieReviewAdapter;
import com.example.noureldeen.movieguide.adapter.MovieVideoAdapter;
import com.example.noureldeen.movieguide.contracts.DetailsActivityContract;
import com.example.noureldeen.movieguide.models.MovieReview;
import com.example.noureldeen.movieguide.models.MovieVideo;
import com.example.noureldeen.movieguide.presenters.DetailsActivityPresenter;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityContract.DetailsActivityView {

    public DetailsActivityPresenter detailsActivityPresenter;
    @BindView(R.id.iv_movie_poster)
    public
    ImageView moviePosterThumbnail_IV;
    @BindView(R.id.tv_movie_title)
    public
    TextView movieTitle_TV;
    @BindView(R.id.tv_movie_synopsis)
    public
    TextView movieOverView_TV;
    @BindView(R.id.rb_movie_rate)
    public
    RatingBar movieVoteAverage_RB;
    @BindView(R.id.tv_movie_release_date)
    public
    TextView movieReleaseDate_TV;
    @BindView(R.id.tv_movie_rate)
    public
    TextView movieVoteAverage_TV;
    @BindView(R.id.rv_movieReviews)
    public
    RecyclerView movieReviews_RV;
    @BindView(R.id.rv_movieVideos)
    public
    RecyclerView movieVideos_RV;
    @BindView(R.id.ib_makeMovieFavorite)
    public
    ImageButton makeMovieFavorite_BTN;
    LinearLayoutManager reviewsRVGridLayoutManager;
    LinearLayoutManager videosRVGridLayoutManager;
    int reviewsRecyclerViewPosition;
    int videosRecyclerViewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        detailsActivityPresenter = new DetailsActivityPresenter(this);
        reviewsRVGridLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        videosRVGridLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        if(savedInstanceState != null) {
            reviewsRecyclerViewPosition = savedInstanceState.getInt("ReviewsRecyclerViewPosition");
            videosRecyclerViewPosition = savedInstanceState.getInt("VideosRecyclerViewPosition");
        }else {
            reviewsRecyclerViewPosition = 0;
            videosRecyclerViewPosition = 0;
        }
        makeMovieFavorite_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsActivityPresenter.favoriteButtonClicked(getMyApplication());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        detailsActivityPresenter.attachDataToRecyclerViews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ReviewsRecyclerViewPosition",reviewsRVGridLayoutManager.findFirstVisibleItemPosition());
        outState.putInt("VideosRecyclerViewPosition",videosRVGridLayoutManager.findFirstVisibleItemPosition());
    }

    @Override
    public void bindDataToViews(String MovieTitle,
                         int MovieID,
                         URL MoviePosterThumbnail,
                         String MovieOverView,
                         float MovieVoteAverage,
                         String MovieReleaseDate) {
        Glide.with(this).load(String.valueOf(MoviePosterThumbnail)).into(moviePosterThumbnail_IV);
        movieTitle_TV.setText(MovieTitle);
        movieOverView_TV.setText(MovieOverView);
        movieVoteAverage_RB.setRating(MovieVoteAverage / 2);
        movieReleaseDate_TV.setText(MovieReleaseDate);
        movieVoteAverage_TV.setText(String.valueOf(MovieVoteAverage));
    }

    @Override
    public void publishMovieReviews(List<MovieReview> movieReviews) {
        movieReviews_RV.setHasFixedSize(true);
        movieReviews_RV.setLayoutManager(reviewsRVGridLayoutManager);
        movieReviews_RV.scrollToPosition(reviewsRecyclerViewPosition);
        movieReviews_RV.setAdapter(new MovieReviewAdapter(movieReviews));
    }

    @Override
    public void publishMovieVideos(List<MovieVideo> movieVideos) {
        movieVideos_RV.setHasFixedSize(true);
        movieVideos_RV.setLayoutManager(videosRVGridLayoutManager);
        movieVideos_RV.scrollToPosition(videosRecyclerViewPosition);
        movieVideos_RV.setAdapter(new MovieVideoAdapter(new MovieVideoAdapter.VideoClickListener() {
            @Override
            public void onClick(int position) {
                detailsActivityPresenter.movieVideoItemClicked(position);
            }
        }, movieVideos));
    }

    @Override
    public Intent getMovieIntent() {
        return getIntent();
    }

    @Override
    public MyApplication getMyApplication() {
        return ((MyApplication)getApplication());
    }

    @Override
    public void favoriteButtonDisable() {
        makeMovieFavorite_BTN.setImageResource(android.R.drawable.btn_star_big_off);
    }

    @Override
    public void favoriteButtonEnable() {
        makeMovieFavorite_BTN.setImageResource(android.R.drawable.btn_star_big_on);

    }
}
