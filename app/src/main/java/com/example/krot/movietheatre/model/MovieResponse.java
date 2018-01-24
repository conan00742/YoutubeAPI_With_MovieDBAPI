package com.example.krot.movietheatre.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Krot on 1/17/18.
 */

public class MovieResponse {

    @SerializedName("results")
    private final List<Movie> movieMappingList;

    public MovieResponse(List<Movie> movieMappingList) {
        this.movieMappingList = movieMappingList;
    }

    public List<Movie> getMovieMappingList() {
        return movieMappingList;
    }
}
