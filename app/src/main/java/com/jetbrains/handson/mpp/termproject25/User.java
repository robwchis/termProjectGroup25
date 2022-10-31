package com.jetbrains.handson.mpp.termproject25;

public class User {

    private int id;
    private String username;
    private String password;
    private boolean admin;

    public User(String u, String p, Boolean adm){
        username = u;
        password = p;
        admin = adm;
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

    public void setAdmin(Boolean admStat){
        admin = admStat;
    }

    public boolean getAdmin(){
        return admin;
    }

}
