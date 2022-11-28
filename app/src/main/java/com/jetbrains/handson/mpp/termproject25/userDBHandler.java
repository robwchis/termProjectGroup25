package com.jetbrains.handson.mpp.termproject25;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class userDBHandler extends SQLiteOpenHelper {
    //Database Variables
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_COURSE1_CODE = "c1code";
    private static final String COLUMN_COURSE1_DAY1 = "c1days";
    private static final String COLUMN_COURSE1_DAY2 = "c1hours";
    private static final String COLUMN_COURSE2_CODE ="c2code";
    private static final String COLUMN_COURSE2_DAY1 = "c2days";
    private static final String COLUMN_COURSE2_DAY2 = "c2hours";
    private static final String COLUMN_COURSE3_CODE ="c3code";
    private static final String COLUMN_COURSE3_DAY1 = "c3days";
    private static final String COLUMN_COURSE3_DAY2 = "c3hours";
    private static final String COLUMN_COURSE4_CODE ="c4code";
    private static final String COLUMN_COURSE4_DAY1 = "c4days";
    private static final String COLUMN_COURSE4_DAY2 = "c4hours";
    private static final String COLUMN_COURSE5_CODE ="c5code";
    private static final String COLUMN_COURSE5_DAY1 = "c5days";
    private static final String COLUMN_COURSE5_DAY2 = "c5hours";

    private static final String DATABASE_NAME = "updatedUsers.db";
    private static final int DATABASE_VERSION = 1;


    public userDBHandler(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase userDB) {

        String createTableCommand = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_COURSE1_CODE + " TEXT, "
                + COLUMN_COURSE1_DAY1 + " TEXT, "
                + COLUMN_COURSE1_DAY2+ " TEXT, "
                + COLUMN_COURSE2_CODE + " TEXT, "
                + COLUMN_COURSE2_DAY1 + " TEXT, "
                + COLUMN_COURSE2_DAY2 + " TEXT, "
                + COLUMN_COURSE3_CODE + " TEXT, "
                + COLUMN_COURSE3_DAY1 + " TEXT, "
                + COLUMN_COURSE3_DAY2 + " TEXT, "
                + COLUMN_COURSE4_CODE + " TEXT, "
                + COLUMN_COURSE4_DAY1 + " TEXT, "
                + COLUMN_COURSE4_DAY2 + " TEXT, "
                + COLUMN_COURSE5_CODE + " TEXT, "
                + COLUMN_COURSE5_DAY1 + " TEXT, "
                + COLUMN_COURSE5_DAY2 + " TEXT)";

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
        values.put(COLUMN_COURSE1_CODE, user.getCourseCode(1));
        values.put(COLUMN_COURSE1_DAY1, user.getCourseDay1(1));
        values.put(COLUMN_COURSE1_DAY2, user.getCourseDay2(1));
        values.put(COLUMN_COURSE2_CODE, user.getCourseCode(2));
        values.put(COLUMN_COURSE2_DAY1, user.getCourseDay1(2));
        values.put(COLUMN_COURSE2_DAY2, user.getCourseDay2(2));
        values.put(COLUMN_COURSE3_CODE, user.getCourseCode(3));
        values.put(COLUMN_COURSE3_DAY1, user.getCourseDay1(3));
        values.put(COLUMN_COURSE3_DAY2, user.getCourseDay2(3));
        values.put(COLUMN_COURSE4_CODE, user.getCourseCode(4));
        values.put(COLUMN_COURSE4_DAY1, user.getCourseDay1(4));
        values.put(COLUMN_COURSE4_DAY2, user.getCourseDay2(4));
        values.put(COLUMN_COURSE5_CODE, user.getCourseCode(5));
        values.put(COLUMN_COURSE5_DAY1, user.getCourseDay1(5));
        values.put(COLUMN_COURSE5_DAY2, user.getCourseDay2(5));



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
