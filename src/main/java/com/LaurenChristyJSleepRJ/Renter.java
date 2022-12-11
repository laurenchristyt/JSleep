package com.LaurenChristyJSleepRJ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.LaurenChristyJSleepRJ.dbjson.Serializable;

/**
 * The `Renter` class represents a person who rents a room.
 *
 * @author  Lauren Christy Tanudjaja
 * @version 1.0
 * @see Serializable
 */
public class Renter extends Serializable
{
    /**
     *  The phone number of a renter.
     */
    public String phoneNumber = " ";
    /**
     *  The address of the renter.
     */
    public String address = " ";
    /**
     *  The username of the renter.
     */
    public String username;
    /**
     *  The regular expression pattern for validating the phone number of the renter
     */
    public static final String REGEX_NAME  = "^[A-Z]{1}\\w{4,20}(?!\\s)$" ;
    /**
     *  The regular expression pattern for validating the username of the renter.
     */
    public static final String REGEX_PHONE = "^\\d{9,12}(?!\\s)$";

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

    /**
     * Constructs a new `Renter` instance with the specified username, phone number, and address.
     *
     * @param username  The username of the renter.
     * @param phoneNumber  The phone number of the renter.
     * @param address  The address of the renter.
     */
    public Renter(String username, String phoneNumber, String address){
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Validates the renter's username and phone number.
     *
     * @return `true` if the username and phone number are valid, `false` otherwise.
     */
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

