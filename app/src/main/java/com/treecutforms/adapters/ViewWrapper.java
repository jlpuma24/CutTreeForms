package com.treecutforms.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V view) {
        super(view);
        this.view = view;
    }

    public V getView() {
        return this.view;
    }
}