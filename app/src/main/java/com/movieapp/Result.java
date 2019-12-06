package com.movieapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("results")
    private List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }

}
