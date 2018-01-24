package com.example.krot.movietheatre.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Krot on 1/18/18.
 */

public class Trailer {

    @NonNull
    private final String trailerId;

    @Nullable
    private final String trailerUrl;

    public Trailer(@NonNull String trailerId, String trailerUrl) {
        this.trailerId = trailerId;
        this.trailerUrl = trailerUrl;
    }

    @NonNull
    public String getTrailerId() {
        return trailerId;
    }

    @Nullable
    public String getTrailerUrl() {
        return trailerUrl;
    }
}
