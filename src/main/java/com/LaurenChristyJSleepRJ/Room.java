package com.LaurenChristyJSleepRJ;

import java.util.ArrayList;
import java.util.Date;
import com.LaurenChristyJSleepRJ.dbjson.Serializable;

/**
 * This class represents a room.
 *
 * @author Lauren Christy Tanudjaja
 * @version 1.0
 */
public class Room extends Serializable
{
    /** The name of the room. */
    public String name;

    /** The size of the room in square meters. */
    public int size;

    /** The price of the room. */
    public Price price;

    /** The facilities available with the room. */
    public ArrayList<Facility> facility= new ArrayList<>();

    /** The address of the room. */
    public String address;

    /** The type of bed in the room. */
    public BedType bedType;

    /** The city where the room is located. */
    public City city;

    /** The ID of the account that owns the room. */
    public int accountId;

    /** The dates on which the room is booked. */
    public ArrayList<Date> booked;

    public Room (int accountId, String name, int size, Price price, ArrayList<Facility> facility, City city, String address, BedType bedType){
        this.accountId = accountId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
        this.city = city;
        this.address = address;
        this.bedType = bedType;
    }

    @Override
    public String toString(){
        return "ID: " + this.id + "\n" + "Name: " + this.name + "\n" + "Bedtype: " + bedType + "\n" + "Size: " + this.size + "\n" +
                this.price + "\n" + "Facility: " + facility + "\n" +
                "City: " + this.city + "\n" + "Address: " + this.address + "\n";
    }

    /*@Override
    public boolean read (String content){
        return false;
    }

    public Object write(){
        return null;
    } */
}
