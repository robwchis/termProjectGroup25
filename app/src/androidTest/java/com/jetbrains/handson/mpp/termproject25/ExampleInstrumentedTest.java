package com.jetbrains.handson.mpp.termproject25;

import android.content.Context;
import android.database.Cursor;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.jetbrains.handson.mpp.termproject25", appContext.getPackageName());
    }
    @Test
    public void TestIfThereIsInstructor(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        adminDBHandler db = new adminDBHandler(appContext);

        Cursor cursor = db.getData();

        while(cursor.moveToNext()){
            assertEquals(cursor.getString(6), ("i"));
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

        while(cursor.moveToNext()){
            assertEquals(cursor.getString(0), "test1");
        }



    }
    @Test
    public void TestCourseCode(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        adminDBHandler db = new adminDBHandler(appContext);

        Cursor cursor = db.getData();


        course c = new course("Test2", "Test2");
        db.addCourse(c);

        while(cursor.moveToNext()){
            assertEquals(cursor.getString(1), "test1");
        }



    }
    @Test
    public void TestDesc(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        adminDBHandler db = new adminDBHandler(appContext);

        Cursor cursor = db.getData();

        String desc = "This is a test. I like turtles";

        course c = new course("Test3", "Test3");
        c.setCourseDesc(desc);
        db.addCourse(c);

        while(cursor.moveToNext()){
            assertEquals(cursor.getString(7), desc);
        }



    }

    @Test
    public void TestAddingFirstCourse() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDBHandler db = new userDBHandler(appContext);


        User u = new User("Test", "ing");

        u.setCourse("c1", "Mon/Tue", "9:00/12:00");

        String hold = u.getCourseCode(1);

        assertEquals(hold, "c1");

    }

    @Test
    public void TestAddingMultipleCourses() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDBHandler db = new userDBHandler(appContext);


        User u = new User("Test", "ing");

        u.setCourse("c1", "Mon/Tue", "9:00/12:00");
        u.setCourse("CSI", "Wed/Fri", "19:00/16:00");

        String hold = u.getCourseCode(1);
        String hold2 = u.getCourseCode(2);

        assertEquals(hold, "c1");
        assertEquals(hold2, "CSI");

    }

    @Test
    public void TestCourseDays1() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDBHandler db = new userDBHandler(appContext);


        User u = new User("Test", "ing");

        u.setCourse("c1", "Mon/Tue", "9:00/12:00");
        u.setCourse("CSI", "Wed/Fri", "19:00/16:00");

        String hold = u.getCourseDay1(1);
        String hold2 = u.getCourseDay1(2);

        assertEquals(hold, "Mon@9:00");
        assertEquals(hold, "Wed@19:00");

    }

    @Test
    public void TestCourseDays2() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDBHandler db = new userDBHandler(appContext);


        User u = new User("Test", "ing");

        u.setCourse("c1", "Mon/Tue", "9:00/12:00");
        u.setCourse("CSI", "Wed/Fri", "19:00/16:00");

        String hold = u.getCourseDay2(1);
        String hold2 = u.getCourseDay2(2);

        assertEquals(hold, "Tue@12:00");
        assertEquals(hold, "Fri@16:00");

    }

}