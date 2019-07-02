package com.example.wordpuzzlegame.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseAccess databaseAccess;

    public DatabaseAccess(Context context) {

//        this.sqLiteOpenHelper = new DBHelper(context);

    }

    public static DatabaseAccess getInstance(Context context) {

        if(databaseAccess == null) {

            databaseAccess = new DatabaseAccess(context);

        }

        return databaseAccess;

    }

    public void open() {
        this.sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        if(sqLiteDatabase != null) {
            this.sqLiteDatabase.close();
        }
    }

}
