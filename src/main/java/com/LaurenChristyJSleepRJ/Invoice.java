package com.LaurenChristyJSleepRJ;

import com.LaurenChristyJSleepRJ.dbjson.Serializable;

public class Invoice extends Serializable {
    public int renterId;
    public int buyerId;
    public RoomRating rating;
    public PaymentStatus status;

    public enum PaymentStatus {
        FAILED, WAITING, SUCCESS
    }

    public enum RoomRating {
        NONE, BAD, NEUTRAL, GOOD
    }

    public Invoice(Account buyer, Renter renter)
    {
        this.renterId = renter.id;
        this.buyerId = buyer.id;

        status = com.LaurenChristyJSleepRJ.Invoice.PaymentStatus.WAITING;
        rating = com.LaurenChristyJSleepRJ.Invoice.RoomRating.NONE;
    }

    protected Invoice(int buyerId, int renterId)
    {

        this.renterId = renterId;
        this.buyerId = buyerId;

        status = PaymentStatus.WAITING;
        rating = RoomRating.NONE;
    }


    public String print() {
        return ("buyer ID : " + buyerId + "\n" + "renter ID: " + renterId + "\n");
    }
}