package com.jetbrains.handson.mpp.termproject25;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class adminDBHandler extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "courses";
    private static final String COLUMN_COURSE_NAME ="name";
    private static final String COLUMN_COURSE_CODE = "code";
    private static final String COLUMN_COURSE_DAYONE = "day1";
    private static final String COLUMN_COURSE_DAYTWO = "day2";
    private static final String COLUMN_COURSE_TIMEONE = "time1";
    private static final String COLUMN_COURSE_TIMETWO = "time2";
    private static final String COLUMN_COURSE_INSTRUCTOR = "instructor";
    private static final String COLUMN_COURSE_DESCRIPTION = "Description";
    private static final String COLUMN_COURSE_CAP = "Capacity";
    private static final String DATABASE_NAME = "newCourseList.db";
    private static final int DATABASE_VERSION = 1;


    public adminDBHandler(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase adminDB) {
        String createTableCommand = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_COURSE_NAME +" TEXT, "
                + COLUMN_COURSE_CODE + " TEXT,"
                + COLUMN_COURSE_DAYONE + " TEXT,"
                + COLUMN_COURSE_DAYTWO + " TEXT,"
                + COLUMN_COURSE_TIMEONE + " TEXT,"
                + COLUMN_COURSE_TIMETWO + " TEXT,"
                + COLUMN_COURSE_INSTRUCTOR + " TEXT,"
                + COLUMN_COURSE_DESCRIPTION + " TEXT,"
                + COLUMN_COURSE_CAP + " INT)";

        adminDB.execSQL(createTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public void addCourse(course c){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //Sets all the values, including the admin value, which is false by defalse* :) (*Default)
        values.put(COLUMN_COURSE_NAME, c.getName());
        values.put(COLUMN_COURSE_CODE, c.getCode());
        values.put(COLUMN_COURSE_DAYONE, "");
        values.put(COLUMN_COURSE_DAYTWO, "");
        values.put(COLUMN_COURSE_TIMEONE, "");
        values.put(COLUMN_COURSE_TIMETWO, "");
        values.put(COLUMN_COURSE_DESCRIPTION, "");
        values.put(COLUMN_COURSE_CAP, 0);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeCourse(course c){
        SQLiteDatabase db = this.getReadableDatabase();
        //Deletes user by their username
        db.delete(TABLE_NAME, "code=?", new String[]{c.getCode()});
        db.close();
    }
}
