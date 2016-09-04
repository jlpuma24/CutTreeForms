package com.treecutforms.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */

public class DatabaseForm extends RealmObject {

    @PrimaryKey
    private long id;
    private Form form;
    private boolean uploaded;
    private String afterPhotoUrl;
    private String beforePhotoUrl;
    private String presentPhotoUrl;
    private String date;

    public DatabaseForm() {
    }

    public DatabaseForm(long id, Form form, boolean uploaded, String afterPhotoUrl, String beforePhotoUrl, String presentPhotoUrl, String date) {
        this.id = id;
        this.form = form;
        this.uploaded = uploaded;
        this.afterPhotoUrl = afterPhotoUrl;
        this.beforePhotoUrl = beforePhotoUrl;
        this.presentPhotoUrl = presentPhotoUrl;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getAfterPhotoUrl() {
        return afterPhotoUrl;
    }

    public void setAfterPhotoUrl(String afterPhotoUrl) {
        this.afterPhotoUrl = afterPhotoUrl;
    }

    public String getBeforePhotoUrl() {
        return beforePhotoUrl;
    }

    public void setBeforePhotoUrl(String beforePhotoUrl) {
        this.beforePhotoUrl = beforePhotoUrl;
    }

    public String getPresentPhotoUrl() {
        return presentPhotoUrl;
    }

    public void setPresentPhotoUrl(String presentPhotoUrl) {
        this.presentPhotoUrl = presentPhotoUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
