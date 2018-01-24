package com.example.krot.movietheatre.model;

import android.support.annotation.Nullable;
import android.widget.ProgressBar;

/**
 * Created by Krot on 1/17/18.
 */

public class MovieItem implements Item {

    @Nullable
    private final Movie movie;

    public MovieItem(Movie movie) {
        this.movie = movie;
    }

    @Nullable
    public Movie getMovie() {
        return movie;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MovieItem) {
            MovieItem currentMovieItem = (MovieItem) obj;
            return (this.getMovie().getMovieId().equals(currentMovieItem.getMovie().getMovieId()));
        } else {
            return false;
        }
    }

    @Override
    public boolean sameContent(Item item) {
        MovieItem currentMovieItem = (MovieItem) item;
        return      (this.getMovie().getMovieTitle().equals(currentMovieItem.getMovie().getMovieTitle())
                &&  (this.getMovie().getMovieOriginalTitle().equals(currentMovieItem.getMovie().getMovieOriginalTitle()))
                &&  (this.getMovie().getMovieOverview().equals(currentMovieItem.getMovie().getMovieOverview()))
                &&  (this.getMovie().getMovieReleaseDate().equals(currentMovieItem.getMovie().getMovieReleaseDate()))
                &&  (this.getMovie().getMovieRating() == currentMovieItem.getMovie().getMovieRating()));
    }
}
