package com.jetbrains.handson.mpp.termproject25;

public class course {
    private String name;
    private String code;
    private String[] courseDays;
    private String[] courseTimes;
    private String courseDesc;
    private int studentCapacity;

    public course(String n, String c){
        name = n;
        code = c;
//        courseDays = new String[2];
        courseDays[0] = "";
        courseDays[1] = "";
//        courseTimes = new String[2];
        courseTimes[0] = "";
        courseTimes[1] = "";
    }

    public void setName(String n){
        name  = n;
    }

    public String getName(){
        return name;
    }

    public void setCode(String C){
        code = C;
    }

    public String getCode(){
        return code;
    }

    public void setCourseDays(String day1, String day2){
        courseDays[0] = day1;
        courseDays[1] = day2;
    }

    public String[] getCourseDays(){
        return courseDays;
    }

    public void setCourseTimes(String time1, String time2){
        courseTimes[0] = time1;
        courseTimes[1] = time2;
    }

    public String[] getCourseTimes(){
        return courseTimes;
    }

    public void setCourseDesc(String desc){
        courseDesc = desc;
    }

    public String getCourseDesc(){
        return courseDesc;
    }

}