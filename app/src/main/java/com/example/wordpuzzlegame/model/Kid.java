package com.example.wordpuzzlegame.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class Kid {

    private long kid_id;
    private String kid_name;
    private String kid_gender;
    private String kid_grade;

    private DataSource dataSource;
    private Context context;

    public Kid(long kid_id, String kid_name, String kid_gender, String kid_grade) {
        this.kid_id = kid_id;
        this.kid_name = kid_name;
        this.kid_gender = kid_gender;
        this.kid_grade = kid_grade;
    }

    public Kid(String name, String gender, String grade) {
        this.kid_name = name;
        this.kid_gender = gender;
        this.kid_grade = grade;
    }

    public Kid(Context context) {

        dataSource = new DataSource(context);
        this.context = context;

    }
    public long getKid_id() {
        return kid_id;
    }

    public void setKid_id(long kid_id) {
        this.kid_id = kid_id;
    }

    public String getKid_name() {
        return kid_name;
    }

    public void setKid_name(String kid_name) {
        this.kid_name = kid_name;
    }

    public String getKid_gender() {
        return kid_gender;
    }

    public void setKid_gender(String kid_gender) {
        this.kid_gender = kid_gender;
    }

    public String getKid_grade() {
        return kid_grade;
    }

    public void setKid_grade(String kid_grade) {
        this.kid_grade = kid_grade;
    }

    public long addKid(Kid kid) {

        ContentValues contentValues =  new ContentValues();
        contentValues.put(ItemTables.KID_NAME, kid.getKid_name());
        contentValues.put(ItemTables.KID_GENDER, kid.getKid_gender());
        contentValues.put(ItemTables.KID_GRADE, kid.getKid_grade());

        return dataSource.createItem(contentValues, ItemTables.KID_TABLE);

    }

    public int deleteKid(long id) {
        return dataSource.deleteByLong(id, ItemTables.KID_TABLE, ItemTables.KID_ID);
    }

    public ArrayList<Kid> kids() {

        Cursor kidCursor = dataSource.getAll(ItemTables.KID_TABLE);

        ArrayList<Kid> kids = new ArrayList<>();

        kidCursor.moveToFirst();

        while(!kidCursor.isAfterLast()) {

            String name = kidCursor.getString(kidCursor.getColumnIndex(ItemTables.KID_NAME));
            String gender = kidCursor.getString(kidCursor.getColumnIndex(ItemTables.KID_GENDER));
            String grade = kidCursor.getString(kidCursor.getColumnIndex(ItemTables.KID_GRADE));
            Kid kid = new Kid(name, gender, grade);
            kids.add(kid);
            kidCursor.moveToNext();

        }

        return kids;
    }
}
