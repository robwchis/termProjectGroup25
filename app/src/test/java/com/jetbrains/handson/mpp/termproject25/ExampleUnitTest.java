package com.jetbrains.handson.mpp.termproject25;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.Cursor;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void TestCourseCode(){

        course c = new course("Test2", "Test2");
        c.setCode("Did It Change?");

            assertEquals(c.getCode(), "Did It Change?");
    }
    @Test
    public void TestCourseNameChange(){

        course c = new course("Test3", "Test3");
        c.setName("Did It Change?");

        assertEquals(c.getName(), "Did It Change?");
    }
    @Test
    public void TestCIfDescChanges(){

        course c = new course("Test2", "Test2");
        c.setCourseDesc("This is the greatest course to ever be created! I like Turtles");

        assertEquals(c.getCourseDesc(), "This is the greatest course to ever be created! I like Turtles");
    }

}

