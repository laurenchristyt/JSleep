package LaurenChristyJSleepRJ;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar; 

public class Payment extends Invoice  {
    
    public Date from;
    public Date to;
    private int roomId;

    public Payment(int id, Account buyer, Renter renter, int roomId, Date
            from, Date to) {
        super(id, buyer, renter);
        this.from = from;
        this.to = to;
        this.roomId = roomId;
    }
    
    public Payment(int id, int buyerId, int renterId, int roomId, Date
            from, Date to) {
        super(id, buyerId, renterId);
        this.from = from;
        this.to = to;
        this.roomId = roomId;

    }

    public String getTime(){
        SimpleDateFormat SDFormat = new SimpleDateFormat("Formatted Date = dd MMMM yyy");
        return SDFormat.format(this.from.getTime());
    }

    public static boolean availability(Date from,Date to,Room room){
        if(room.booked.isEmpty()) {
            return true;
        }
        for (Date i : room.booked){
            if((i.after(from) && i.before(to)) || i.equals(from))
                return false;
        }

        return true;
    }

    public static boolean makeBooking(Date from,Date to,Room room){
        if(to.before(from))
            return false;

        if(availability(from, to, room)){
            while (from.before(to)){
                room.booked.add(from);
                Calendar c = Calendar.getInstance();
                c.setTime(from);
                c.add(Calendar.DATE, 1);
                from = c.getTime();
            } return true;

        } return false;

    }

    public String print() {
        return 	"Buyer Id : " + buyerId + "\n" + "Renter Id : " + renterId + "\n" + "Time : " + time + "\n" + "Room Id : " + roomId + "\n" +
                "To : " + to + "\n" + "From : " + from;
                
    }

    public int getRoomId(){
        return this.roomId;
    }


}

}