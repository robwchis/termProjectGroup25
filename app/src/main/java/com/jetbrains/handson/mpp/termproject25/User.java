package com.jetbrains.handson.mpp.termproject25;

public class User {

    private int id;
    private String username;
    private String password;

    public User(String u, String p, int adm){
        username = u;
        password = p;
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

}
