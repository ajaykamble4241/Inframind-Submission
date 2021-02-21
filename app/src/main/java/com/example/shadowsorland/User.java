package com.example.shadowsorland;

public class User {

    public String Name, email, dob, Addr,phone,gender;
    public float heart,bo,tem,bp,glucose,respiration;

    public User(){

    }

    public User(String Name, String email, String dob, String Addr, String phone, String gender, float heart, float bo, float tem, float bp, float glucose, float respiration){
        this.Name= Name;
        this.email= email;
        this.phone= phone;
        this.dob= dob;
        this.Addr= Addr;
        this.gender= gender;
        this.heart= heart;
        this.bo= bo;
        this.tem= tem;
        this.bp= bp;
        this.glucose= glucose;
        this.respiration= respiration;
    }
}
