package com.treecutforms.utils;

/**
 * Created by Usuario on 14/07/2016.
 */
public class AppSession {

    private static AppSession singletonObject;
    private String currentPhotoPath;

    public AppSession() {
    }

    public static synchronized AppSession getInstance() {
        if (singletonObject == null) {
            singletonObject = new AppSession();
        }
        return singletonObject;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }
}
