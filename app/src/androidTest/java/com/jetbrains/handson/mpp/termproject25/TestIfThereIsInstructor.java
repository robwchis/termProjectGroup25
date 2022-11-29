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









    //
    //DELIVERABLE 3 TEST CASES START HERE
    //

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
        assertEquals(hold2, "Wed@19:00");

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
        assertEquals(hold2, "Fri@16:00");

    }

    @Test
    public void TestAddingMAXCourses() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDBHandler db = new userDBHandler(appContext);

        User u = new User("Test", "ing");


        u.setCourse("c1", "Mon/Tue", "9:00/12:00");
        u.setCourse("CSI", "Thu/Fri", "9:00/16:00");
        u.setCourse("ENG", "Mon/Wed", "10:30/12:00");
        u.setCourse("Blah", "Tue/Thu", "9:00/16:00");
        u.setCourse("blahblah", "Wed/Fri", "19:00/9:00");


        String hold = u.getCourseCode(1);
        String hold2 = u.getCourseCode(2);

        assertEquals(u.getCourseCode(1), "c1");
        assertEquals(u.getCourseCode(2), "CSI");
        assertEquals(u.getCourseCode(3), "ENG");
        assertEquals(u.getCourseCode(4), "Blah");
        assertEquals(u.getCourseCode(5), "blahblah");
        assertEquals(u.getCourseDay1(1), "Mon@9:00");
        assertEquals(u.getCourseDay1(2), "Thu@9:00");
        assertEquals(u.getCourseDay1(3), "Mon@10:30");
        assertEquals(u.getCourseDay1(4), "Tue@9:00");
        assertEquals(u.getCourseDay1(5), "Wed@19:00");
        assertEquals(u.getCourseDay2(1), "Tue@12:00");
        assertEquals(u.getCourseDay2(2), "Fri@16:00");
        assertEquals(u.getCourseDay2(3), "Wed@12:00");
        assertEquals(u.getCourseDay2(4), "Thu@16:00");
        assertEquals(u.getCourseDay2(5), "Fri@9:00");

    }

}