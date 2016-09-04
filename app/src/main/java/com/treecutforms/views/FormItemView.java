package com.treecutforms.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.treecutforms.R;

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

    public void bind(String formIndex) {
        ((TextView) findViewById(R.id.tv_form_name)).setText(getContext().getString(
                R.string.form_item_view_from_index_format, formIndex));
    }

}
