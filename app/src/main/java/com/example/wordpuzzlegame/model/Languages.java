package com.example.wordpuzzlegame.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class Languages implements Parcelable {

    private long lang_id;
    private String language;
    private String alphabets;

    private DataSource dataSource;

    public Languages(long lang_id, String language, String alphabets) {
        this.lang_id = lang_id;
        this.language = language;
        this.alphabets = alphabets;
    }

    public Languages(String language, String alphabets) {

        this.language = language;
        this.alphabets = alphabets;

    }
    public Languages(Context context) {

        dataSource = new DataSource(context);

    }

    protected Languages(Parcel in) {
        lang_id = in.readLong();
        language = in.readString();
        alphabets = in.readString();
    }

    public static final Creator<Languages> CREATOR = new Creator<Languages>() {
        @Override
        public Languages createFromParcel(Parcel in) {
            return new Languages(in);
        }

        @Override
        public Languages[] newArray(int size) {
            return new Languages[size];
        }
    };

    public long getLang_id() {
        return lang_id;
    }

    public void setLang_id(long lang_id) {
        this.lang_id = lang_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(String alphabets) {
        this.alphabets = alphabets;
    }

    public ArrayList<Languages> languagesList() {

        Cursor cursor = dataSource.getAllLanguages();

        ArrayList<Languages> languagesList = new ArrayList<>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            long id = cursor.getLong(0);
            String lang = cursor.getString(1);
            String alphabets = cursor.getString(2);

            Languages languages = new Languages(id, lang, alphabets);
            languagesList.add(languages);

            cursor.moveToNext();
        }

        return languagesList;

    }

    public long insertLanguage(Languages languages) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemTables.LANGUAGE, languages.getLanguage());
        contentValues.put(ItemTables.ALPHABETS, languages.getAlphabets());

        return dataSource.createItem(contentValues, ItemTables.LANGUAGE_TABLE);

    }

    public Languages singleLanguage(long id) {

        Cursor singleData = dataSource.singleItem(id, ItemTables.LANGUAGE_TABLE);

        if(singleData != null) {

            singleData.moveToFirst();
            long langId = singleData.getLong(singleData.getColumnIndex(ItemTables.LANG_ID));
            String language = singleData.getString(singleData.getColumnIndex(ItemTables.LANGUAGE));
            String alphabets = singleData.getString(singleData.getColumnIndex(ItemTables.ALPHABETS));

            return new Languages(langId, language, alphabets);

        } else {

            return null;

        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(lang_id);
        dest.writeString(language);
        dest.writeString(alphabets);
    }

    public boolean deleteLanguage(long lang_id) {

        int numOfRowsForWords = 0;
        int numOfRowsForLanguage = dataSource.deleteByLong(lang_id, ItemTables.LANGUAGE_TABLE, ItemTables.LANG_ID);

        Log.i("Delete value", String.valueOf(numOfRowsForLanguage));

        if(numOfRowsForLanguage != 0) {
            numOfRowsForWords = dataSource.deleteByLong(lang_id, ItemTables.WORD_TABLE, ItemTables.LANG_ID);
        }
        Log.i("Delete value", String.valueOf(numOfRowsForWords));

        return numOfRowsForWords != 0;
    }
}
