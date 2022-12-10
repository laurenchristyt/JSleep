package com.LaurenChristyJSleepRJ;

import java.util.ArrayList;
import java.util.Date;
import com.LaurenChristyJSleepRJ.dbjson.Serializable;

public class Room extends Serializable
{
    public int accountId;
    public int size;
    public String name;
    public String address;
    public ArrayList<Facility> facility = new ArrayList<Facility>();
    public Price price;
    public BedType bedType;
    public City city;
    public ArrayList<Date> booked = new ArrayList<Date>();

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
