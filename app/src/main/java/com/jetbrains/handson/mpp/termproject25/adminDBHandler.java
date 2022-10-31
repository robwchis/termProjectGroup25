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
    private static final String DATABASE_NAME = "courses.db";
    private static final int DATABASE_VERSION = 1;


    public adminDBHandler(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableCommand = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_COURSE_NAME +" TEXT, "
                + COLUMN_COURSE_CODE + " TEXT)";

        sqLiteDatabase.execSQL(createTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Cursor getData(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public void addCourse(course c){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //Sets all the values, including the admin value, which is false by defalse* :) (*Default)
        values.put(COLUMN_COURSE_NAME, c.getName());
        values.put(COLUMN_COURSE_CODE, c.getCode());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeUser(course c){
        SQLiteDatabase db = this.getReadableDatabase();
        //Deletes user by their username
        db.delete(TABLE_NAME, "code=?", new String[]{c.getCode()});
        db.close();
    }
}
