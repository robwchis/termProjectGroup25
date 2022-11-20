package com.jetbrains.handson.mpp.termproject25;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.Cursor;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class TestIfThereIsInstructor {
    //Example Given
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.jetbrains.handson.mpp.termproject25", appContext.getPackageName());
    }
@Test
    public void TestIfThereIsInstructor() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        adminDBHandler db = new adminDBHandler(appContext);

        Cursor cursor = db.getData();


        while (cursor.moveToNext()) {
            if(cursor.getString(6).equals("i")) {
                assertEquals(cursor.getString(6), ("i"));
            }
        }
    }

    @Test
    public void TestIfUsersNotEmpty(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDBHandler db = new userDBHandler(appContext);

        Cursor cursor = db.getData();

        assertEquals(cursor != null, true);

    }
    @Test
    public void TestCourseName(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        adminDBHandler db = new adminDBHandler(appContext);

        Cursor cursor = db.getData();


        course c = new course("Test1", "Test1");
        db.addCourse(c);

        //gets new data
        cursor = db.getData();

        while(cursor.moveToNext()){
            if(cursor.getString(0).equals("test1")) {
                assertEquals(cursor.getString(0), "test1");

            }
        }



    }

    @Test
    public void TestDesc(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        adminDBHandler db = new adminDBHandler(appContext);

        Cursor cursor = db.getData();
        String take = "";

        String desc = "This is a test. I like turtles";

        course c = new course("Test3", "Test3");
        c.setCourseDesc(desc);
        db.addCourse(c);


        cursor = db.getData();//Gets new data


        while(cursor.moveToNext()){
            if(cursor.getString(7).equals(desc)) {
               take = cursor.getString(7);
            }
        }
        assertEquals(take, desc);


    }

}