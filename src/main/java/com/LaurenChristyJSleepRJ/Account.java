package com.LaurenChristyJSleepRJ;

import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.LaurenChristyJSleepRJ.dbjson.Serializable;

public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public double balance;
    public Renter renter;

    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z.]+\\.[a-zA-Z]+?$";



    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean Validate(){
        Pattern emailPattern = Pattern.compile(this.REGEX_EMAIL);
        Pattern passwordPattern = Pattern.compile(this.REGEX_PASSWORD);
        Matcher emailMatcher = emailPattern.matcher(this.email);
        Matcher passwordMatcher = passwordPattern.matcher(this.password);

        return passwordMatcher.find() && emailMatcher.find();
    }

    public String toString(){
        return "ID: " + this.id + "\n" + "Name: " + this.name + "\n" + "Email: " + this.email + "\n" + "Password: " + this.password + "\n";
    }




}