package LaurenChristyJSleepRJ;

public class Invoice extends Serializable
{
    public int buyerId;
    public int renterId;
    public String time;
    
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.buyerId= buyer.id;
        this.renterId= renter.id;
        this.time = time;
    }
    public Invoice(int id, int buyerId, int renterId, String time){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    public String print(){
        return "Buyer ID = " + this.buyerId + "\n" + "Renter ID = " + this.renterId + "\n" + "Time =" + this.time + "\n";
    }
}
