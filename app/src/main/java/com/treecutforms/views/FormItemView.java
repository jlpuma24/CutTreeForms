package com.treecutforms.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.treecutforms.R;
import com.treecutforms.listeners.OnUploadButtonSelected;
import com.treecutforms.models.DatabaseForm;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */

public class FormItemView extends RelativeLayout {

    public FormItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.form_item_view, this, true);
    }

    public void bind(final DatabaseForm form, @Nullable final OnUploadButtonSelected callback) {
        ((TextView) findViewById(R.id.tv_form_name)).setText(getContext().getString(
                R.string.form_item_view_from_index_format, String.valueOf(form.getId())));
        ImageButton imageButton = ((ImageButton) findViewById(R.id.ib_refresh));
        if (!form.isUploaded()) {
            imageButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_refresh));
            (findViewById(R.id.ib_refresh)).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback != null) callback.onButtonClicked(form);
                }
            });
        } else {
            imageButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_done));
        }
    }

}
