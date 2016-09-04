package com.treecutforms.database;

import com.treecutforms.listeners.OnDataBaseSave;
import com.treecutforms.models.DatabaseForm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */

public class DataBaseHelper {

    private Realm realm;
    private static DataBaseHelper instance;

    public static DataBaseHelper getInstance() {
        if (instance == null) instance = new DataBaseHelper();
        return instance;
    }

    private DataBaseHelper() {
        this.realm = Realm.getDefaultInstance();
    }

    public void addForm(final DatabaseForm form, final OnDataBaseSave callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(form);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess();

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.onError();
            }
        });
    }

    public ArrayList<DatabaseForm> getForms() {
        RealmResults<DatabaseForm> results = realm.where(DatabaseForm.class).findAll();
        return new ArrayList<>(results.subList(0, results.size()));
    }

    public void updateUploadedForm(final Long id, final OnDataBaseSave callback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DatabaseForm result = realm.where(DatabaseForm.class).equalTo("id", id).findFirst();
                result.setUploaded(true);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callback.onError();
            }
        });
    }
}
