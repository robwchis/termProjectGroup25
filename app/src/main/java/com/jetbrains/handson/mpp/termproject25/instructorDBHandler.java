package com.jetbrains.handson.mpp.termproject25;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class instructorDBHandler extends SQLiteOpenHelper {

    //Database Variables
    private static final String TABLE_NAME = "instructors";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ADMIN = "admin";
    private static final String DATABASE_NAME = "instructors.db";
    private static final int DATABASE_VERSION = 1;


    public instructorDBHandler(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase userDB) {

        String createTableCommand = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_USERNAME +" TEXT, "
                + COLUMN_PASSWORD + " TEXT)";

        userDB.execSQL(createTableCommand);

    }

    @Override
    public void onUpgrade(SQLiteDatabase userDB, int oldV, int newV ) {
        userDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(userDB);
    }

    //gets all data in the Database, returns as an iteratable cursor
    public Cursor getData(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //Sets all the values, including the admin value, which is false by defalse* :) (*Default)
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeUser(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        //Deletes user by their username
        //This is kind of a placeholder for future delieverables
        db.delete(TABLE_NAME, "username=?", new String[]{user.getUsername()});
        db.close();
    }
}

