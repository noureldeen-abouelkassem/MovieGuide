package com.example.noureldeen.movieguide.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noureldeen.movieguide.R;
import com.example.noureldeen.movieguide.models.MovieReview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noureldeen on 1/22/2018.
 */

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewHolder> {

    private final List<MovieReview> movieReviews;

    public MovieReviewAdapter(List<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
    }

    @Override
    public MovieReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieReviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_movie_review,parent,false));
    }

    @Override
    public void onBindViewHolder(MovieReviewHolder holder, int position) {
        holder.bindMovieReviewHolder(movieReviews.get(position).getAuthor(),movieReviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return movieReviews.size();
    }

    class MovieReviewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvAuthorName)
        TextView authorTextView;
        @BindView(R.id.tvReviewContent)
        TextView reviewTextView;
        MovieReviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        void bindMovieReviewHolder(String authorName, String reviewContent){
            authorTextView.setText(authorName);
            reviewTextView.setText(reviewContent.trim());
        }
    }
}
