package com.jetbrains.handson.mpp.termproject25;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.Cursor;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
public class TestingAndroidDel3 {

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
