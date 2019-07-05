package com.example.wordpuzzlegame.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceUtil {

    private static final String PREFERENCE_NAME = "preference_name";
    public static final String PARENT_ACTIVE = "parent_active";
    public static final String ACTIVE_USER_ID = "active_user)id";

    private Context context;

    public PreferenceUtil(Context context){
        this.context = context;
    }

    private SharedPreferences.Editor editor(){
        return context.getSharedPreferences(PREFERENCE_NAME, 0).edit();
    }

    private SharedPreferences preferences(int mode){ // this is for retrieving info
        // from sharedPreference
        return context.getSharedPreferences(PREFERENCE_NAME, mode);
    }

    public void saveBooleanValue(boolean b, String booleanName) {

        SharedPreferences.Editor editor = editor();
        editor.putBoolean(booleanName, b);
        editor.apply();

    }

    public void saveLongValue(long s, String longName) {

        SharedPreferences.Editor editor = editor();
        editor.putLong(longName, s);
        editor.apply();

    }

    public boolean retrieveBooleanValue(String name){
        return preferences(MODE_PRIVATE).getBoolean(name, false);
    }

    public long retrieveLongValue(String name) {
        return preferences(MODE_PRIVATE).getLong(name, -1);
    }

}
