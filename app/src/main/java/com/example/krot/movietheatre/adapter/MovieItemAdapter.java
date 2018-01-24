package com.example.krot.movietheatre.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.krot.movietheatre.R;
import com.example.krot.movietheatre.bus.RxBus;
import com.example.krot.movietheatre.viewholder.ItemBaseViewHolder;
import com.example.krot.movietheatre.viewholder.MovieItemViewHolder;

/**
 * Created by Krot on 1/17/18.
 */

public class MovieItemAdapter extends ItemBaseAdapter {

    @NonNull
    private final RxBus bus;

    private final Context context;

    public MovieItemAdapter(@NonNull RxBus bus, Context context) {
        this.bus = bus;
        this.context = context;
    }

    @Override
    public ItemBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieItemViewHolder(parent, R.layout.movie_item, bus, context);
    }
}
