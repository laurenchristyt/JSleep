package com.LaurenChristyJSleepRJ;

import com.LaurenChristyJSleepRJ.dbjson.Serializable;
/**
 * A class that represents a customer complaint.
 *
 * This class extends the `Serializable` class and adds fields for the date and description of a complaint.
 * It also provides a `toString()` method that returns a string representation of the complaint.
 *
 * @author Lauren Christy Tanudjaja
 * @version 1.0
 * @see Serializable
 */
public class Complaint extends Serializable
{
    /**
     *  The date to make a complaint.
     */
    public String date;

    /**
     *  The description of a complaint.
     */
    public String desc;

    /**
     * Constructs a new Complaint.
     *
     * @param date The date to make a complaint.
     * @param desc The discount that will be applied to the original price
     */
    public Complaint(String date, String desc) {
        this.date = date;
        this.desc = desc;
    }

    /**
     * This method is used to show the date and description of a complaint.
     *
     * @return String of date and description
     */
    public String toString(){
        return "Date:" + this.date + "\n" + "Desc: " + this.desc + "\n";
    }
}
