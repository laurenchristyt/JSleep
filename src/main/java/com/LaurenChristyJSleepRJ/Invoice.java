package com.LaurenChristyJSleepRJ;

import java.util.Date;
import java.util.Calendar;

public class Invoice extends Serializable
{
    public enum PaymentStatus
    {
    FAILED, WAITING, SUCCESS
    }
    public enum RoomRating
    {
    NONE, BAD, NEUTRAL, GOOD
    }
    
    public int buyerId;
    public int renterId;
    public Date time;
    public PaymentStatus status;
    public RoomRating rating;
    
    public Invoice(Account buyer, Renter renter){
        this.buyerId= buyer.id;
        this.renterId= renter.id;
        this.time = new Date();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    protected Invoice(int buyerId, int renterId){
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Date();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    public String print(){
        return "Id : " + this.id + "\n" + "Buyer ID = " + this.buyerId + "\n" + "Renter ID = " + this.renterId + "\n" + "Time =" + this.time + "\n";
    }
}
