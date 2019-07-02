package com.example.wordpuzzlegame.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "puzzle.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {

//        db.execSQL(ItemTables.CREATE_ENGLISH_TABLE);
//        db.execSQL(ItemTables.CREATE_AMHARIC_TABLE);
//        db.execSQL(ItemTables.CREATE_TIGIRIGNA_TABLE);
//
//    }
//
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//


    }
}
