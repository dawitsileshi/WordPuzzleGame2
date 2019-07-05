package com.example.wordpuzzlegame.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class Reward {

    private long reward_id;
    private long kid_id;
    private String game_type;
    private int secondsOrHeart;
    private long lang_id;

    private Context context;
    private DataSource dataSource;

    public Reward(Context context) {
        this.context = context;
        dataSource = new DataSource(context);
    }

    private Reward(long kidId, int secondsOrHeart) {
        this.kid_id = kidId;
        this.secondsOrHeart = secondsOrHeart;
    }

    public long getReward_id() {
        return reward_id;
    }

    public void setReward_id(long reward_id) {
        this.reward_id = reward_id;
    }

    public long getKid_id() {
        return kid_id;
    }

    public void setKid_id(long kid_id) {
        this.kid_id = kid_id;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public int getSecondsOrHeart() {
        return secondsOrHeart;
    }

    public void setSecondsOrHeart(int secondsOrHeart) {
        this.secondsOrHeart = secondsOrHeart;
    }

    public long getLang_id() {
        return lang_id;
    }

    public void setLang_id(long lang_id) {
        this.lang_id = lang_id;
    }

    public long createOrUpdateRewardHeart(Reward reward, String langName, String columnName) {

        Cursor cursor;

        long result;

        cursor = dataSource.getSqLiteDatabase().query(ItemTables.REWARD_TABLE, null, ItemTables.KID_ID  + " = ? AND " + ItemTables.LANG_ID + " = ? AND " + ItemTables.QUIZ_TYPE + " = ?",
                new String[]{String.valueOf(reward.getKid_id()), String.valueOf(reward.getLang_id()), reward.getGame_type()}, null, null, null, null);

            ContentValues contentValues = new ContentValues();
            contentValues.put(ItemTables.KID_ID, reward.getKid_id());
            contentValues.put(columnName, reward.getSecondsOrHeart());
            contentValues.put(ItemTables.LANG_ID, reward.getLang_id());
        if(cursor.getCount() == 0) {
            result = dataSource.createItem(contentValues, ItemTables.REWARD_TABLE);
        } else {
            cursor.moveToFirst();
            long rewardId = cursor.getLong(cursor.getColumnIndex(ItemTables.REWARD_ID));
            result = dataSource.updateItem(contentValues, ItemTables.REWARD_TABLE, ItemTables.REWARD_ID, rewardId);
        }

        cursor.close();
        return result;

    }

//    public long createOrUpdateReward(Reward reward, String langName) {
//
//        Cursor cursor;
//
//        long result;
//
//        cursor = dataSource.getSqLiteDatabase().query(ItemTables.REWARD_TABLE, null, ItemTables.KID_ID  + " = ? AND " + ItemTables.LANG_ID + " = ? AND " + ItemTables.QUIZ_TYPE + " = ?",
//                new String[]{String.valueOf(reward.getKid_id()), String.valueOf(reward.getLang_id()), reward.getGame_type()}, null, null, null, null);
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ItemTables.KID_ID, reward.getKid_id());
//        contentValues.put(ItemTables.HEART_VALUE, reward.getSecondsOrHeart());
//        contentValues.put(ItemTables.LANG_ID, reward.getLang_id());
//        if(cursor.getCount() == 0) {
//            result = dataSource.createItem(contentValues, ItemTables.REWARD_TABLE);
//        } else {
//            cursor.moveToFirst();
//            long rewardId = cursor.getLong(cursor.getColumnIndex(ItemTables.REWARD_ID));
//            result = dataSource.updateItem(contentValues, ItemTables.REWARD_TABLE, ItemTables.REWARD_ID, rewardId);
//        }
//
//        return result;
//
//    }
}
