package com.jetbrains.handson.mpp.termproject25;

public class User {

    private int id;
    private String username;
    private String password;
    private String course1Day1;
    private String course1Code;
    private String course1Day2;
    private String course2Day1;
    private String course2Code;
    private String course2Day2;
    private String course3Day1;
    private String course3Code;
    private String course3Day2;
    private String course4Day1;
    private String course4Code;
    private String course4Day2;
    private String course5Day1;
    private String course5Code;
    private String course5Day2;

    public User(String u, String p, int adm){
        username = u;
        password = p;

        course1Code = "";
        course1Day1 = "";
        course1Day2 = "";
        course2Code = "";
        course2Day1 = "";
        course2Day2 = "";
        course3Code = "";
        course3Day1 = "";
        course3Day2 = "";
        course4Code = "";
        course4Day1 = "";
        course4Day2 = "";
        course5Code = "";
        course5Day1 = "";
        course5Day2 = "";
    }

    public void setId(int nID){
        id =nID;
    }

    public int getID(){
        return id;
    }

    public void setUsername(String n){
        username = n;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String pass){
        password = pass;
    }

    public String getPassword(){
        return password;
    }

    public void setCourse(String code, String days, String hours){
        String[] d = days.split("/");
        String[] h = hours.split("/");
        if(course1Code.equals("")){
            course1Code = code;
            course1Day1 = d[0] +"@"+h[0];
            course1Day2 = d[1] +"@"+h[1];
        } else if(course2Code.equals("")){
            course2Code = code;
            course2Day1 = d[0] +"@"+h[0];
            course2Day2 = d[1] +"@"+h[1];
        } else if(course3Code.equals("")){
            course3Code = code;
            course3Day1 = d[0] +"@"+h[0];
            course3Day2 = d[1] +"@"+h[1];
        }else if(course4Code.equals("")){
            course4Code = code;
            course4Day1 = d[0] +"@"+h[0];
            course4Day2 = d[1] +"@"+h[1];
        }else if(course5Code.equals("")){
            course5Code = code;
            course5Day1 = d[0] +"@"+h[0];
            course5Day2 = d[1] +"@"+h[1];
        } else {
            //returns an error message
        }
    }

    public String getCourseCode(int courseNumber){

        if(courseNumber == 1){
            return course1Code;
        } else if(courseNumber == 2){
            return course2Code;
        } else if(courseNumber == 3){
            return course3Code;
        } else if(courseNumber == 4){
            return course4Code;
        } else if(courseNumber == 5){
            return course5Code;
        }
        return "null";
    }

    public String getCourseDay1(int courseNumber){

        if(courseNumber == 1){
            return course1Day1;
        } else if(courseNumber == 2){
            return course2Day1;
        } else if(courseNumber == 3){
            return course3Day1;
        } else if(courseNumber == 4){
            return course4Day1;
        } else if(courseNumber == 5){
            return course5Day1;
        }
        return "null";
    }

    public String getCourseDay2(int courseNumber){

        if(courseNumber == 1){
            return course1Day2;
        } else if(courseNumber == 2){
            return course2Day2;
        } else if(courseNumber == 3){
            return course3Day2;
        } else if(courseNumber == 4){
            return course4Day2;
        } else if(courseNumber == 5){
            return course5Day2;
        }
        return "null";
    }

}
