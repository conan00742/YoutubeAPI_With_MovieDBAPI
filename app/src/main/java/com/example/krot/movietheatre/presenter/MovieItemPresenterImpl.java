package com.example.krot.movietheatre.presenter;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.krot.movietheatre.MovieItemContract;
import com.example.krot.movietheatre.model.Item;
import com.example.krot.movietheatre.model.Movie;
import com.example.krot.movietheatre.model.MovieItem;
import com.example.krot.movietheatre.model.MovieResponse;
import com.example.krot.movietheatre.repository.MovieItemRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Krot on 1/17/18.
 */

public class MovieItemPresenterImpl implements MovieItemContract.MovieItemPresenter {



    @Nullable
    private MovieItemContract.MovieItemView movieItemView;

    @NonNull
    private List<Item> movieList = new ArrayList<>();

    Disposable loadingDisposable;

    public MovieItemPresenterImpl(MovieItemContract.MovieItemView movieItemView) {
        this.movieItemView = movieItemView;
    }

    //    @Override
//    protected void onCleared() {
//        super.onCleared();
//        if (!loadingDisposable.isDisposed()) {
//            loadingDisposable.dispose();
//        }
//
//    }


    @Override
    public void loadData() {
        if (movieItemView != null) {
            if (movieList.isEmpty()) {
                movieItemView.showLoadingIndicator();
                movieList = new ArrayList<>();

                //disposable
                loadingDisposable = MovieItemRepository.fetchData().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<MovieResponse>() {
                            @Override
                            public void accept(MovieResponse movieResponse) throws Exception {
                                if (movieResponse != null) {
                                    movieItemView.hideLoadingIndicator();
                                }
                                List<Movie> receivedMovieList = movieResponse.getMovieMappingList();
                                for (int i = 0; i < receivedMovieList.size(); i++) {
                                    receivedMovieList.get(i).setMovieId(Movie.fakeMovieIdList().get(i));
                                    MovieItem currentMovieItem = new MovieItem(receivedMovieList.get(i));
                                    movieList.add(currentMovieItem);
                                }
                                movieItemView.updateMovieItemList(movieList);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                movieItemView.hideLoadingIndicator();
                                Log.i("WTF", "throwable = " + throwable);
                                movieItemView.showError();
                            }
                        });
            }

            else {
                movieItemView.updateMovieItemList(movieList);
            }


        }
    }


    @Override
    public void removeMovie(int position) {
        if (movieItemView != null) {
            List<Item> newItemList = new ArrayList<>();
            for (int i = 0; i < movieList.size(); i++) {
                Item currentItem = movieList.get(i);
                if (i == position) {
                    continue;
                }

                newItemList.add(currentItem);
            }

            movieList = newItemList;
            movieItemView.updateMovieItemList(movieList);
        }

    }

    @Override
    public void insertMovie(MovieItem movieItem, int position) {
        if (movieItemView != null) {
            List<Item> newItemList = new ArrayList<>();
            for (int i = 0; i < movieList.size(); i++) {
                Item currentItem = movieList.get(i);
                if (i == position) {
                    newItemList.add(position, movieItem);
                    newItemList.add(currentItem);
                } else {
                    newItemList.add(currentItem);
                }
            }

            movieList = newItemList;
            movieItemView.updateMovieItemList(movieList);
        }
    }

    @Override
    public String getMovieIdByPosition(int position) {
        if (position >= 0 && position < movieList.size()) {
            MovieItem currentMovieItem = (MovieItem) movieList.get(position);
            return (currentMovieItem.getMovie().getMovieId());
        } else {
            return null;
        }
    }

    @Override
    public String getMovieNameByPosition(int position) {
        if (position >= 0 && position < movieList.size()) {
            MovieItem currentMovieItem = (MovieItem) movieList.get(position);
            return (currentMovieItem.getMovie().getMovieTitle());
        } else {
            return null;
        }
    }

}
