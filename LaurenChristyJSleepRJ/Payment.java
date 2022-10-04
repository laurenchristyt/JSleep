package LaurenChristyJSleepRJ;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Payment extends Invoice
{
    public Calendar to;
    public Calendar from;
    private int roomId;
    
    public Payment(int id, int buyerId, int renterId, int roomId){
        super(id, buyerId, renterId);
        this.to = Calendar.getInstance();
        this.from = Calendar.getInstance();
        this.to.add(Calendar.DATE, 2);
        this.roomId = roomId;
    }
    public Payment(int id, Account buyer, Renter renter, int roomId){
        super(id, buyer, renter);
        this.to = Calendar.getInstance();
        this.from = Calendar.getInstance();
        this.to.add(Calendar.DATE, 2);
        this.roomId = roomId;
    }
    public String print(){
        return "From:" + this.from + "\n" + "To:" + this.to + "\n" + "Room ID: " + this.roomId;
    }
    public int getRoomId(){
        return roomId;
    }
    public String getTime(){
        SimpleDateFormat formattedTime = new SimpleDateFormat ("dd MMMM yyyy");
        String strTime = formattedTime.format(this.time.getTime()); 
        return "Formatted Date: " + strTime;
    }
    public String getDuration(){
        SimpleDateFormat formattedTime = new SimpleDateFormat ("dd MMMM yyyy");
        String strFrom = formattedTime.format(this.from.getTime());
        String strTo = formattedTime.format(this.to.getTime());
        return strFrom + " - " + strTo;
    }
}

