package com.example.krot.movietheatre.bus;

import com.example.krot.movietheatre.model.MovieItem;
import com.example.krot.movietheatre.viewholder.MovieItemViewHolder;

/**
 * Created by Krot on 1/22/18.
 */

public class EventPlaybackVideo {

    private final MovieItem movieItem;
    private final MovieItemViewHolder movieItemViewHolder;

    public EventPlaybackVideo(MovieItem movieItem, MovieItemViewHolder movieItemViewHolder) {
        this.movieItem = movieItem;
        this.movieItemViewHolder = movieItemViewHolder;
    }

    public MovieItem getMovieItem() {
        return movieItem;
    }

    public MovieItemViewHolder getMovieItemViewHolder() {
        return movieItemViewHolder;
    }
}
