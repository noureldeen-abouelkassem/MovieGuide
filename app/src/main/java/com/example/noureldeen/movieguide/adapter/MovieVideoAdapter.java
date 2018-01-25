package com.example.noureldeen.movieguide.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noureldeen.movieguide.R;
import com.example.noureldeen.movieguide.models.MovieVideo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noureldeen on 1/22/2018.
 */

public class MovieVideoAdapter extends RecyclerView.Adapter<MovieVideoAdapter.MovieVideoHolder> {
    private final VideoClickListener videoClickListener;
    private final List<MovieVideo> movieVideos;
    public interface VideoClickListener{
        void onClick(int position);
    }

    public MovieVideoAdapter(VideoClickListener videoClickListener, List<MovieVideo> movieVideos) {
        this.videoClickListener = videoClickListener;
        this.movieVideos = movieVideos;
    }

    @Override
    public MovieVideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieVideoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_movie_video,parent,false));
    }

    @Override
    public void onBindViewHolder(final MovieVideoHolder holder, int position) {
        holder.bindMovieVideoHolder(movieVideos.get(position).getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoClickListener.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieVideos.size();
    }

    class MovieVideoHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvVideoName)
        TextView textView;
         MovieVideoHolder(View itemView) {
            super(itemView);
             ButterKnife.bind(this,itemView);
        }
        void bindMovieVideoHolder(String movieVideoTitle){
            textView.setText(movieVideoTitle.trim());
        }
    }
}
