package com.example.wordpuzzlegame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static com.example.wordpuzzlegame.model.DatabaseAccess databaseAccess;

    public DatabaseAccess(Context context) {

//        this.sqLiteOpenHelper = new DBHelper(context);

    }

    public static com.example.wordpuzzlegame.model.DatabaseAccess getInstance(Context context) {

        if(databaseAccess == null) {

            databaseAccess = new com.example.wordpuzzlegame.model.DatabaseAccess(context);

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
