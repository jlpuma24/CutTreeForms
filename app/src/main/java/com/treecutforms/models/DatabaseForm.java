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


    public DatabaseForm() {
    }

    public DatabaseForm(long id, Form form, boolean uploaded) {
        this.id = id;
        this.form = form;
        this.uploaded = uploaded;
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
}
