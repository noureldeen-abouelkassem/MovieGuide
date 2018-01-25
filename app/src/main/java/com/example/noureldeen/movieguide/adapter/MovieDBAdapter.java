package com.example.noureldeen.movieguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.noureldeen.movieguide.R;
import com.example.noureldeen.movieguide.models.Movie;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noureldeen on 1/20/2018.
 */

public class MovieDBAdapter extends RecyclerView.Adapter<MovieDBAdapter.MovieViewHolder> {
    private final Context context;
    private final List<Movie> movies;
    private final MoviePosterClick moviePosterClick;
    public interface MoviePosterClick{
        void OnClick(int position);
    }

    public MovieDBAdapter(Context context, List<Movie> movies, MoviePosterClick moviePosterClick) {
        this.context = context;
        this.movies = movies;
        this.moviePosterClick = moviePosterClick;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        if(holder != null){
            holder.bindMoviePoster(Movie.moviePoster(movies.get(position).getPosterPath()));
            holder.moviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moviePosterClick.OnClick(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie_poster)
        ImageView moviePoster;
        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        void bindMoviePoster(URL posterURL){
            try {
                Glide.with(context).load(String.valueOf(posterURL.toURI())).into(moviePoster);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
