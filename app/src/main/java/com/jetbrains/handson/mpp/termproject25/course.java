package com.jetbrains.handson.mpp.termproject25;

public class course {
    private String name;
    private String code;

    public course(String n, String c){
        name = n;
        code = c;
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

}
