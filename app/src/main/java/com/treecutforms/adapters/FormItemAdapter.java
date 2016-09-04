package com.treecutforms.adapters;

import android.view.ViewGroup;

import com.treecutforms.listeners.OnUploadButtonSelected;
import com.treecutforms.models.DatabaseForm;
import com.treecutforms.views.FormItemView;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */

public class FormItemAdapter extends RecyclerViewBaseAdapter<DatabaseForm, FormItemView> {

    private OnUploadButtonSelected callback;

    public FormItemAdapter(OnUploadButtonSelected callback) {
        this.callback = callback;
    }

    @Override
    protected FormItemView onCreateItemView(ViewGroup parent, int viewType) {
        return new FormItemView(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewWrapper<FormItemView> holder, int position) {
        holder.getView().bind(getItems().get(position), callback);
    }
}
