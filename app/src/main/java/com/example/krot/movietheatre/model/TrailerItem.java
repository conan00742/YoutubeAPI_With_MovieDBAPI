package com.example.krot.movietheatre.model;

import android.support.annotation.Nullable;

/**
 * Created by Krot on 1/18/18.
 */

public class TrailerItem implements Item {

    @Nullable
    private final Trailer trailer;

    public TrailerItem(Trailer trailer) {
        this.trailer = trailer;
    }

    @Nullable
    public Trailer getTrailer() {
        return trailer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TrailerItem) {
            TrailerItem currentTrailerItem = (TrailerItem) obj;
            return (this.getTrailer().getTrailerId().equals(currentTrailerItem.getTrailer().getTrailerId()));
        } else {
            return false;
        }
    }

    @Override
    public boolean sameContent(Item item) {
        TrailerItem currentTrailerItem = (TrailerItem) item;
        return (this.getTrailer().getTrailerUrl().equals(currentTrailerItem.getTrailer().getTrailerUrl()));
    }
}
