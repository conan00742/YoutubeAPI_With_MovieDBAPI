package com.example.krot.movietheatre.bus;

/**
 * Created by Krot on 1/22/18.
 */

public class EventMoveToTrailer {

    private final int position;

    public EventMoveToTrailer(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
