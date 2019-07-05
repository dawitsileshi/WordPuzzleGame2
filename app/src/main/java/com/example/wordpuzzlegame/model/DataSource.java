package com.example.wordpuzzlegame.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;

public class DataSource {

    private SQLiteDatabase sqLiteDatabase;
//    private DBHelper dbHelper;
    Context context;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public DataSource(Context context) {
        this.context = context;
        this.sqLiteOpenHelper = new DBHelper(context);
        sqLiteDatabase = this.sqLiteOpenHelper.getWritableDatabase();
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public Cursor getAllElements(String lang) {

        String tableName;
        String sortMethod;

//        switch (lang) {
//            case "TIGRIGNA":
//
//                tableName = ItemTables.TABLE_TIGIRIGNA;
//                sortMethod = ItemTables.TIGIRIGNA_WORD;
//
//                break;
//            case "AMHARIC":
//
//                tableName = ItemTables.TABLE_AMHARIC;
//                sortMethod = ItemTables.AMHARIC_WORD;
//
//
//                break;
//            default:
//
//                tableName = ItemTables.TABLE_ENGLISH;
//                sortMethod = ItemTables.ENGLISH_WORD;
//
//                break;
//        }

        return sqLiteDatabase.query(ItemTables.WORD_TABLE, null, null, null, null, null,  null);

    }

    public Cursor getAllLanguages() {

        return sqLiteDatabase.query(ItemTables.LANGUAGE_TABLE, null, null, null, null, null, null);

    }

    public Cursor assetCursor(long word_code) {

        return sqLiteDatabase.query(ItemTables.ASSETS_TABLE, null, ItemTables.WORD_CODE + "=" + word_code, null, null, null, null);

    }

    public Cursor items(long id, String column, String tableName) {
        return sqLiteDatabase.query(tableName, null, column + "=" + id, null, null, null, null);
    }

    public Cursor words(long lang_id, String column, String tableName) {

        return sqLiteDatabase.query(tableName, null, column + "=" + lang_id, null, null, null, null);

    }

    public Cursor getAll(String tableName) {

        return sqLiteDatabase.query(tableName, null, null, null, null, null, null);

    }
    public ArrayList<Integer> getTheRandomIndices(int startIndex, int endIndex) {

        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            integers.add(i);
        }

        Collections.shuffle(integers);

        return integers;

    }

    public long createItem(ContentValues contentValues, String tableName) {

        return sqLiteDatabase.insert(tableName, null, contentValues);

    }

    public Cursor singleItem(long id, String tableName) {

        Cursor cursor = null;

        if(tableName == ItemTables.LANGUAGE_TABLE) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.LANG_ID + " = " + id, null,
                    null, null, null);

        } else if(tableName.equals(ItemTables.WORD_TABLE)) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.WORD_ID + " = " + id, null,
                    null, null, null);

        } else if(tableName.equals(ItemTables.ASSETS_TABLE)) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.ASSETS_ID+ " = " + id, null,
                    null, null, null);

        } else if(tableName.equals(ItemTables.RESULTS_TABLE)) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.RESULT_ID + " = " + id, null,
                    null, null, null);

        } else if(tableName.equals(ItemTables.KID_TABLE)) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.KID_ID + " = " + id, null,
                    null, null, null);

        } else if(tableName.equals(ItemTables.PARENT_TABLE)) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.PARENT_ID + " = " + id, null,
                    null, null, null);

        } else if(tableName.equals(ItemTables.STRUGGLING_WORD_TABLE)) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.STRUGGLING_WORD_ID + " = " + id, null,
                    null, null, null);

        } else if(tableName.equals(ItemTables.ANSWER_TABLE)) {

            cursor = sqLiteDatabase.query(tableName, null, ItemTables.ANSWER_ID + " = " + id, null,
                    null, null, null);

        }

        return cursor;
    }

    public int deleteByLong(long value, String tableName, String column) {
        return sqLiteDatabase.delete(tableName, column + " =  " + value, null);
    }

    public Cursor loginParent(String email) {
        return sqLiteDatabase.query(ItemTables.PARENT_TABLE, new String[]{ItemTables.PARENT_EMAIL, ItemTables.PARENT_PASSWORD}, ItemTables.PARENT_EMAIL + " = '" + email + "'", null, null, null, null);
    }

    public long updateItem(ContentValues contentValues, String tableName, String column, long columnValue){
        return sqLiteDatabase.update(tableName, contentValues, column + " = ?", new String[]{String.valueOf(columnValue)});
    }
}
