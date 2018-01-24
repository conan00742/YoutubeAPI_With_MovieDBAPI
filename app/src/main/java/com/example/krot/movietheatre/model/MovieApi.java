package com.example.krot.movietheatre.model;

import com.example.krot.movietheatre.model.MovieResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Krot on 1/17/18.
 */

public interface MovieApi {

    @GET("movie/top_rated")
    Single<MovieResponse> getAllTopRatedMovies(@Query("api_key") String apiKey);

}
