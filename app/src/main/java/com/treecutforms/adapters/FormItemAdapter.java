package com.treecutforms.adapters;

import android.view.ViewGroup;

import com.treecutforms.models.Form;
import com.treecutforms.views.FormItemView;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */

public class FormItemAdapter extends RecyclerViewBaseAdapter<Form, FormItemView> {

    @Override
    protected FormItemView onCreateItemView(ViewGroup parent, int viewType) {
        return new FormItemView(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewWrapper<FormItemView> holder, int position) {
        holder.getView().bind(String.valueOf(position + 1));
    }
}
