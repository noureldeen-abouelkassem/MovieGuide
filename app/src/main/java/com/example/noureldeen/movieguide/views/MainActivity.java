package com.example.noureldeen.movieguide.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.noureldeen.movieguide.MyApplication;
import com.example.noureldeen.movieguide.R;
import com.example.noureldeen.movieguide.adapter.MovieDBAdapter;
import com.example.noureldeen.movieguide.contracts.MainActivityContract;
import com.example.noureldeen.movieguide.models.Movie;
import com.example.noureldeen.movieguide.presenters.MainActivityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.MainActivityView {
    @BindView(R.id.rv_movies)
    public
    RecyclerView recyclerView;
    @BindView(R.id.pb_indicator)
    public
    ProgressBar progressBar;
    private MainActivityPresenter mainActivityPresenter;
    GridLayoutManager gridLayoutManager;
    int recyclerViewPosition;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("RecyclerViewPosition",gridLayoutManager.findFirstVisibleItemPosition());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gridLayoutManager = new GridLayoutManager(this,2);
        mainActivityPresenter = new MainActivityPresenter(this);
        if(savedInstanceState != null) {
            recyclerViewPosition = savedInstanceState.getInt("RecyclerViewPosition");
        }else {
            recyclerViewPosition = 0;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainActivityPresenter.attachDataToRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_popular_search){
            mainActivityPresenter.popularItemSelected();
        }else if (item.getItemId() == R.id.action_top_rated_search){
            mainActivityPresenter.topratedItemSelected();
        }else if (item.getItemId() == R.id.action_favorite_search){
            mainActivityPresenter.favoriteItemSelected();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void publishMovies(final List<Movie> movies) {
        gridLayoutManager.scrollToPosition(recyclerViewPosition);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MovieDBAdapter(this, movies, new MovieDBAdapter.MoviePosterClick() {
            @Override
            public void OnClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("movieID",movies.get(position).getId());
                bundle.putString("movieTitle", movies.get(position).getTitle());
                bundle.putString("movieThumbnail", Movie.moviePosterThumbnail(movies.get(position).getPosterPath()).toString());
                bundle.putFloat("movieRate", movies.get(position).getVoteAverage().floatValue());
                bundle.putString("movieReleaseDate", movies.get(position).getReleaseDate());
                bundle.putString("movieOverView", movies.get(position).getOverview());
                mainActivityPresenter.posterImageClicked(MainActivity.this,bundle);
            }
        }));
    }

    @Override
    public void showWaitingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWaitingIndicator() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public MyApplication getMyApplication() {
        return ((MyApplication) getApplication());
    }

}