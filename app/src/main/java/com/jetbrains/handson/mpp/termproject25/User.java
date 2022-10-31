package com.jetbrains.handson.mpp.termproject25;

public class User {

    private int id;
    private String username;
    private String password;
    private int admin;

    public User(String u, String p, int adm){
        username = u;
        password = p;
        admin = adm;//If is 1, user is an admin
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

    public void setAdmin(int admStat){
        admin = admStat;
    }

    public int getAdmin(){
        return admin;
    }

}
