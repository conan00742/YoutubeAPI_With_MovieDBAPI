package com.example.krot.movietheatre.model;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Krot on 1/17/18.
 */

public class MovieApiFetcher {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit movieFetcherRetrofit = null;

    public static Retrofit getMovieFetcherRetrofit() {
        if (movieFetcherRetrofit == null) {
            movieFetcherRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return movieFetcherRetrofit;
    }
}
