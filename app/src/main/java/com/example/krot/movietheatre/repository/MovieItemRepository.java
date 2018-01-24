package com.example.krot.movietheatre.repository;

import com.example.krot.movietheatre.model.MovieApi;
import com.example.krot.movietheatre.model.MovieApiFetcher;
import com.example.krot.movietheatre.model.MovieItem;
import com.example.krot.movietheatre.model.MovieResponse;

import io.reactivex.Single;

/**
 * Created by Krot on 1/22/18.
 */

public class MovieItemRepository {

    private static final String API_KEY = "06c24322fac248016fbd9db1829cfa7e";

    static final MovieApi client = MovieApiFetcher.getMovieFetcherRetrofit().create(MovieApi.class);

    public static Single<MovieResponse> fetchData() {
        return client.getAllTopRatedMovies(API_KEY);
    }


}
