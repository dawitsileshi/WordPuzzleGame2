package com.example.wordpuzzlegame.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class Assets {

    private long asset_id;
    private byte[] image;
    private long word_code;

    private DataSource dataSource;
    private Context context;

    public Assets(byte[] image, long word_code) {
        this.image = image;
        this.word_code = word_code;
    }

    public Assets(long asset_id, byte[] image, long word_code) {
        this.asset_id = asset_id;
        this.image = image;
        this.word_code = word_code;
    }

    public Assets(Context context) {
        dataSource = new DataSource(context);
    }

    public byte[] getImage() {
        return image;
    }

    public Context getContext() {
        return context;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public long getWord_code() {
        return word_code;
    }

    public long getAsset_id() {
        return asset_id;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setAsset_id(long asset_id) {
        this.asset_id = asset_id;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setWord_code(long word_code) {
        this.word_code = word_code;
    }

    public Assets singleAsset(long word_code) {

        Cursor cursor = dataSource.assetCursor(word_code);
        Log.i("Size of the cursor", String.valueOf(cursor.getCount()));

        cursor.moveToFirst();
        long asset_id = cursor.getLong(cursor.getColumnIndex(ItemTables.ASSETS_ID));
        byte[] image = cursor.getBlob(cursor.getColumnIndex(ItemTables.IMAGE));
        int wc = cursor.getInt(cursor.getColumnIndex(ItemTables.WORD_CODE));

        return new Assets(asset_id, image, wc);

    }

    public long insertAssets(Assets assets) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemTables.IMAGE, assets.getImage());
        contentValues.put(ItemTables.WORD_CODE, assets.getWord_code());
        return dataSource.createItem(contentValues, ItemTables.ASSETS_TABLE);

    }

}
