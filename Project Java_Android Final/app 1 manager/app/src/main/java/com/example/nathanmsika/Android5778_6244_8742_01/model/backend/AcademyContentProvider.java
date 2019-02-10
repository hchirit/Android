package com.example.nathanmsika.Android5778_6244_8742_01.model.backend;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;

/**
 * Created by nathanmsika on 18/11/2017.
 */

public class AcademyContentProvider extends ContentProvider{

    DB_manager manager = FactoryDB_manager_.getinstance();


    public AcademyContentProvider() {

    }




    final String TAG = "MyApp";


    @Override
    public Cursor query(Uri uri, String[] projection, Bundle queryArgs, CancellationSignal cancellationSignal) {
        return super.query(uri, projection, queryArgs, cancellationSignal);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete" + uri.toString());

        String listName = uri.getLastPathSegment();
        long id = ContentUris.parseId(uri);

        return 0;
    }

    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType" + uri.toString());
        long id = -1;
        String listName = uri.getLastPathSegment();
        return null;
    }





    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert " + uri.toString());
        manager.db_all(this.getContext());
        String listName = uri.getLastPathSegment();
        long id = -1;
        switch (listName) {
            case "clients":
                String   id_client = manager.addclient(values);
                return ContentUris.withAppendedId(uri, id);

            case "branches":
                id = manager.addbranch(values);
                return ContentUris.withAppendedId(uri, id);

            case "models":
                String id_model = manager.addmodel(values);
                return ContentUris.withAppendedId(uri, id);
            case "cars":
                id = manager.addcar(values);
                return ContentUris.withAppendedId(uri, id);

        }
        return null;
    }




    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query" + uri.toString());
        String listName = uri.getLastPathSegment();
        Log.d(TAG, "merde" + listName);
        manager.db_all(this.getContext());
        // String s = AcademyContract.Student.STUDENT_URI.getLastPathSegment();
        switch (listName) {
            case "clients":
                return manager.Allclient();
            case "branches":
                return manager.Allbranch();
            case "cars":
                return manager.Allcar();
            case "models":
                return manager.Allmodels();

        }
        return null;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG, "update" + uri.toString());
        return 0;
    }


}
