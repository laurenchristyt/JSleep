package com.LaurenChristyJSleepRJ;

import com.LaurenChristyJSleepRJ.dbjson.Serializable;

/**
 * A class that represents an invoice for a rental transaction.
 *
 * <p>This class extends the `Serializable` class and adds fields for the IDs of the buyer and renter, the payment status, and the rating of the rental. It also provides a `print()` method that returns a string representation of the invoice.</p>
 *
 * @author Lauren Christy Tanudjaja
 * @see Serializable
 */
public class Invoice extends Serializable {
    /**
     *  The ID of a Renter
     */
    public int renterId;
    /**
     *  The ID of a Buyer
     */
    public int buyerId;
    /**
     * This enum represents the different types of rating the room can have.
     */
    public RoomRating rating;
    /**
     *  The status of payment
     */
    public PaymentStatus status;

    /**
     * This enum represents the different types of payment status the room can have.
     */
    public enum PaymentStatus {
        FAILED, WAITING, SUCCESS
    }

    public enum RoomRating {
        NONE, BAD, NEUTRAL, GOOD
    }

    /**
     * Constructs a new Invoice.
     *
     * @param buyer The account that makes a booking
     * @param renter The renter that provides the room
     */
    public Invoice(Account buyer, Renter renter)
    {
        this.renterId = renter.id;
        this.buyerId = buyer.id;

        status = com.LaurenChristyJSleepRJ.Invoice.PaymentStatus.WAITING;
        rating = com.LaurenChristyJSleepRJ.Invoice.RoomRating.NONE;
    }

    /**
     * Constructs a new Invoice.
     *
     * @param buyerId The account that makes a booking
     * @param renterId The renter that provides the room
     */
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