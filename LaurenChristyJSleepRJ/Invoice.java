package LaurenChristyJSleepRJ;

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
    public Calendar time;
    public PaymentStatus status;
    public RoomRating rating;
    
    public Invoice(int id, Account buyer, Renter renter){
        super(id);
        this.buyerId= buyer.id;
        this.renterId= renter.id;
        this.time = Calendar.getInstance();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    protected Invoice(int id, int buyerId, int renterId){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = Calendar.getInstance();
        this.rating = RoomRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    public String print(){
        return "Buyer ID = " + this.buyerId + "\n" + "Renter ID = " + this.renterId + "\n" + "Time =" + this.time + "\n";
    }
}
