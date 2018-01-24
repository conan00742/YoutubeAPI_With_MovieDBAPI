package com.example.krot.movietheatre.callback;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.krot.movietheatre.model.Item;

import java.util.List;

/**
 * Created by Krot on 1/17/18.
 */

public class MovieUtilCallback extends DiffUtil.Callback {

    @Nullable
    private List<Item> oldList;

    @Nullable
    private List<Item> newList;

    public MovieUtilCallback(List<Item> oldList, List<Item> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return (oldList != null ? oldList.size() : 0);
    }

    @Override
    public int getNewListSize() {
        return (newList != null ? newList.size() : 0);
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return (oldList.get(oldItemPosition).equals(newList.get(newItemPosition)));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return (oldList.get(oldItemPosition).sameContent(newList.get(newItemPosition)));
    }
}
