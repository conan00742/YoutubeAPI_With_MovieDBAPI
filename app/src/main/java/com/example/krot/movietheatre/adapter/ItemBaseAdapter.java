package com.example.krot.movietheatre.adapter;

import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.example.krot.movietheatre.callback.MovieUtilCallback;
import com.example.krot.movietheatre.model.Item;
import com.example.krot.movietheatre.viewholder.ItemBaseViewHolder;

import java.util.List;

/**
 * Created by Krot on 1/17/18.
 */

public abstract class ItemBaseAdapter extends RecyclerView.Adapter<ItemBaseViewHolder> {

    @Nullable
    private List<Item> currentItemList;

    @MainThread
    public void updateListItem(@Nullable List<Item> newItemList) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MovieUtilCallback(currentItemList, newItemList), false);
        currentItemList = newItemList;
        result.dispatchUpdatesTo(ItemBaseAdapter.this);
    }

    @Nullable
    public List<Item> getCurrentItemList() {
        return currentItemList;
    }

    public void setCurrentItemList(@Nullable List<Item> currentItemList) {
        this.currentItemList = currentItemList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ItemBaseViewHolder holder, int position) {
        holder.bindData(getItemAt(position));
    }

    @Override
    public int getItemCount() {
        return (currentItemList != null ? currentItemList.size() : 0);
    }

    public Item getItemAt(int position) {
        return (currentItemList != null ? currentItemList.get(position) : null);
    }
}
