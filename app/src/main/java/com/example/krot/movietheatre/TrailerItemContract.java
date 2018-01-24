package com.example.krot.movietheatre;

/**
 * Created by Krot on 1/18/18.
 */

public interface TrailerItemContract {

    interface TrailerItemView {
        void loadTrailer();
    }

    interface TrailerItemPresenter {
        void fetchVideoFromUrl();
    }
}
