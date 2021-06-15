package com.example.edressing.ui.models;

/**
 * Created by $(USER) on $(DATE).
 */
public class User {

    private String uid;
    private String name;
    private String email;
    private String city;

    public User(){}

    public User(String uid, String name, String email,String city){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.city = city;
    }

    //GETTERS----
    public String getUid() { return this.uid;}
    public String getUserName() { return this.name;}
    public String getEmail() { return this.email;}
    public String getCity(){ return  this.city;}

    //SETTERS----
    public void setUid(String uid){ this.uid = uid;}
    public void setUserName(String name){ this.name = name;}
    public void setEmail(String email){ this.email = email;}
    public void setCity(String city) { this.city = city;}
}
