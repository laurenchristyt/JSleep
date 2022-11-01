package LaurenChristyJSleepRJ;

import java.util.ArrayList;
import java.util.Date;

public class Room extends Serializable
{
    public int size;
    public String name;
    public Facility facility;
    public Price price;
    public City city;
    public String address;
    public BedType bedType;
    public ArrayList<Date> booked;
    public int accountId;
    
    public Room (int accountId, String name, int size, Price price, Facility facility, City city, String address){
        this.accountId = accountId;
    	this.name = name;
        this.size = size;
        this.price = price;
        this.facility = facility;
        this.city = city;
        this.address = address;
        this.bedType = BedType.SINGLE;
        this.booked = new ArrayList<Date>();
    } 
    public String toString(){
        return "ID: " + this.id + "\n" + "Name: " + this.name + "\n" + "Bedtype: " + this.bedType + "\n" + "Size: " + this.size + "\n" + 
        this.price + "\n" + "Facility: " + this.facility + "\n" + 
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
