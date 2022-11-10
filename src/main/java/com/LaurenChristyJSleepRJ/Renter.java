package com.LaurenChristyJSleepRJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.LaurenChristyJSleepRJ.dbjson.Serializable;
public class Renter extends Serializable
{
    public String phoneNumber;
    public String address;
    public String username;

    public static final String REGEX_NAME = "^[A-Z]{1}\\w{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$";

    /*
    public Renter(int id, String username){
        this.username = username;
        this.address = "";
        this.phoneNumber = 0;
    }
    public Renter(int id, String username, String address){
        this.username = username;
        this.address = address;
        this.phoneNumber = 0;
    }
    public Renter(int id, String username, String phoneNumber){
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    */

    public Renter(String username, String phoneNumber, String address){
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean Validate() {
        Pattern namePat = Pattern.compile(REGEX_NAME);
        Matcher nameMatch = namePat.matcher(username);
        boolean nameFound = nameMatch.find();
        Pattern numPat = Pattern.compile(REGEX_PHONE);
        Matcher numMatch = numPat.matcher(phoneNumber);
        boolean numFound = numMatch.find();
        if(nameFound && numFound)
            return true;
        else
            return false;
    }
}