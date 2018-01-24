package com.example.krot.movietheatre.bus;

import android.support.annotation.NonNull;

import com.example.krot.movietheatre.viewholder.MovieItemViewHolder;

/**
 * Created by Krot on 1/18/18.
 */

public class EventRemoveMovie {

    @NonNull
    private final MovieItemViewHolder movieItemViewHolder;


    public EventRemoveMovie(@NonNull MovieItemViewHolder movieItemViewHolder) {
        this.movieItemViewHolder = movieItemViewHolder;
    }

    @NonNull
    public MovieItemViewHolder getMovieItemViewHolder() {
        return movieItemViewHolder;
    }
}
