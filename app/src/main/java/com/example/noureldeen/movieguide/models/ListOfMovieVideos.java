package com.example.noureldeen.movieguide.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by noureldeen on 1/21/2018.
 */

public class ListOfMovieVideos {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<MovieVideo> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieVideo> getResults() {
        return results;
    }

    public void setResults(List<MovieVideo> results) {
        this.results = results;
    }
}
