package com.treecutforms;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.treecutforms.utils.PrefsUtil;

/**
 * Created by Usuario on 14/07/2016.
 */
public class TreeCutFormsApplication extends Application {

    private static TreeCutFormsApplication context;
    private RequestManager glide;

    @Override
    public void onCreate() {
        super.onCreate();
        glide = Glide.with(this);
        PrefsUtil.initializeInstance(this);
    }

    public static TreeCutFormsApplication getInstance() {
        return context;
    }

    public RequestManager getGlideInstance() {
        return glide;
    }
}
