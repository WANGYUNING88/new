package com.example.sqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wang on 2018/4/3.
 */

public class MyProvider extends ContentProvider {
    private SQLiteDatabase db;
    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private final int COMPANY = 1;
    private final int COMPANYS = 2;
    @Override
    public boolean onCreate() {

        uriMatcher.addURI("com.example.sqlite","company/#",COMPANY);
        uriMatcher.addURI("com.example.sqlite","companys",COMPANYS);
        db = SQLiteDatabase.openOrCreateDatabase(
                "/data/data/"
                        +"com.example.sqlite"
                        +"/databases/mysql.db" ,null);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (uriMatcher.match(uri)){
            case COMPANY:
                long id = ContentUris.parseId(uri);
                String where = "id="+id;
                if (selection != null && !selection.equals("")){
                    where = where +" and " +selection;
                }
                return   db.query("COMPANY",projection,where,selectionArgs,null,null,sortOrder);
            case COMPANYS:
                return   db.query("COMPANY",projection,selection,selectionArgs,null,null,sortOrder);

            default:
                try {
                    throw new Exception("111") ;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        switch (uriMatcher.match(uri)){
            case COMPANY:
                return null;
            case COMPANYS:
                long id = db.insert("COMPANY", null, values);
                if (id>0){
                    return Uri.parse("content://com.example.sqlite//company/"+id);
                }
                return null;

        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
