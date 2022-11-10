package com.LaurenChristyJSleepRJ;
import com.LaurenChristyJSleepRJ.dbjson.Serializable;



public class Invoice extends Serializable {
    public int buyerId;
    public int renterId;
    public PaymentStatus status;
    public RoomRating rating;
    /**
     * Inner enum
     */
    public enum RoomRating {
        NONE, BAD, NEUTRAL, GOOD
    }

    public enum PaymentStatus {
        FAILED, WAITING, SUCCESS
    }

    /**
     * Constructor for objects of class Invoice
     */
    protected Invoice(int buyerId, int renterId) {
        this.buyerId = buyerId;
        this.renterId = renterId;
        status = PaymentStatus.WAITING;
        rating = RoomRating.NONE;
    }

    public Invoice(Account buyer, Renter renter) {
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        status = PaymentStatus.WAITING;
        rating = RoomRating.NONE;
    }

    /**
     * @return String dari attribute dari kelas Invoice
     */
    public String print() {
        return ("Buyer ID : " + buyerId + ", Renter ID : " + renterId);
    }
}
