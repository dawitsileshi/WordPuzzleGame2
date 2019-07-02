package com.example.wordpuzzlegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.wordpuzzlegame.model.DataSource;
import com.example.wordpuzzlegame.model.ItemTables;

public class Parent {

    private long parent_id;
    private String parent_name;
    private String parent_gender;
    private String parent_relation;
    private String parent_email;
    private String parent_password;

    private DataSource dataSource;
    private Context context;

    public Parent(long parent_id, String parent_name, String parent_gender, String parent_relation) {
        this.parent_id = parent_id;
        this.parent_name = parent_name;
        this.parent_gender = parent_gender;
        this.parent_relation = parent_relation;
    }

    public Parent(long parent_id, String parent_name, String parent_gender, String parent_relation, String parent_email, String parent_password) {
        this.parent_id = parent_id;
        this.parent_name = parent_name;
        this.parent_gender = parent_gender;
        this.parent_relation = parent_relation;
        this.parent_email = parent_email;
        this.parent_password = parent_password;
    }

    public Parent(String name, String gender, String relation, String email, String password) {
        parent_name = name;
        parent_gender = gender;
        parent_relation = relation;
        parent_email = email;
        parent_password = password;
    }
    public Parent(Context context) {

        this.context = context;
        dataSource = new DataSource(context);

    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_gender() {
        return parent_gender;
    }

    public void setParent_gender(String parent_gender) {
        this.parent_gender = parent_gender;
    }

    public String getParent_relation() {
        return parent_relation;
    }

    public void setParent_relation(String parent_relation) {
        this.parent_relation = parent_relation;
    }

    public String getParent_email() {
        return parent_email;
    }

    public void setParent_email(String parent_email) {
        this.parent_email = parent_email;
    }

    public String getParent_password() {
        return parent_password;
    }

    public void setParent_password(String parent_password) {
        this.parent_password = parent_password;
    }

    public long register(Parent parent) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemTables.PARENT_NAME, parent.getParent_name());
        contentValues.put(ItemTables.PARENT_GENDER, parent.getParent_gender());
        contentValues.put(ItemTables.PARENT_RELATION, parent.getParent_relation());
        contentValues.put(ItemTables.PARENT_EMAIL, parent.getParent_email());
        contentValues.put(ItemTables.PARENT_PASSWORD, parent.getParent_password());

        return dataSource.createItem(contentValues, ItemTables.PARENT_TABLE);

    }

    public int loginParent(String email, String password) {

        Cursor parentCursor = dataSource.loginParent(email);

        if(parentCursor.getCount() != 0) {
            parentCursor.moveToFirst();

            String pass = parentCursor.getString(parentCursor.getColumnIndex(ItemTables.PARENT_PASSWORD));
            if(pass.equals(password)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }

    }

    public boolean checkParent() {
        Cursor cursor = dataSource.getAll(ItemTables.PARENT_TABLE);

        return cursor.getCount() != 0;
    }

    public Parent singleParent() {

        Cursor cursor = dataSource.getAll(ItemTables.PARENT_TABLE);
        cursor.moveToFirst();

        long parentId = cursor.getLong(cursor.getColumnIndex(ItemTables.PARENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(ItemTables.PARENT_NAME));
        String gender = cursor.getString(cursor.getColumnIndex(ItemTables.PARENT_GENDER));
        String email = cursor.getString(cursor.getColumnIndex(ItemTables.PARENT_EMAIL));
        String relation = cursor.getString(cursor.getColumnIndex(ItemTables.PARENT_EMAIL));
        String password = cursor.getString(cursor.getColumnIndex(ItemTables.PARENT_PASSWORD));

        return new Parent(parentId, name, gender, relation, email, password);

    }
}
