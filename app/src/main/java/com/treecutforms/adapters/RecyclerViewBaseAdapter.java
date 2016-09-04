package com.treecutforms.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */
public abstract class RecyclerViewBaseAdapter<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    private ArrayList<T> items = new ArrayList<>();

    public void setItems(ArrayList<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public ArrayList<T> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);
}