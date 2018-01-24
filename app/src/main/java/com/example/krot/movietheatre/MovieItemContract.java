package com.example.krot.movietheatre;

import android.content.Context;

import com.example.krot.movietheatre.model.Item;
import com.example.krot.movietheatre.model.MovieItem;

import java.util.List;

/**
 * Created by Krot on 1/17/18.
 */

public interface MovieItemContract {

    interface MovieItemView {
        void updateMovieItemList(List<Item> newMovieItemList);
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showError();
    }

    interface MovieItemPresenter {
        void loadData();
        void removeMovie(int position);
        void insertMovie(MovieItem movieItem, int position);
        String getMovieIdByPosition(int position);
        String getMovieNameByPosition(int position);
    }
}
