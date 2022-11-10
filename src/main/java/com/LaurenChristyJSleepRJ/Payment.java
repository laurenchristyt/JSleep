package com.LaurenChristyJSleepRJ;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.RequestParam;
public class Payment extends Invoice {
    public Date to;
    public Date from;
    private int roomId;

    /**
     * Constructor for objects of class Payment
     */
    public Payment(int buyerId, int renterId, int roomId,
                   Date from, Date to) {
        super(buyerId, renterId);
        this.roomId = roomId;
        this.from = from;
        this.to = to;
    }

    public Payment(Account buyer, Renter renter, int roomId,
                   Date from, Date to) {
        super(buyer, renter);
        this.roomId = roomId;
        this.from = from;
        this.to = to;
    }

    /**
     * @return String dari attribute dari kelas Payment
     */
    public String print() {
        return ("To : " + to + ", from : " + from + ", Room ID : " + roomId);
    }

    /**
     * @return roomId (Getter)
     */
    public int getRoomId() {
        return roomId;
    }

    public static boolean availability(Date from, Date to, Room room) {
        Calendar start = Calendar.getInstance();
        start.setTime(from);
        Calendar end = Calendar.getInstance();
        end.setTime(to);
        if(start.after(end) || start.equals(end)){
            return false;
        }
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            if(room.booked.contains(date)){
                return false;
            }
        }
        return true;
    }


    public static boolean makeBooking(Date from, Date to, Room room) {
        if(availability(from, to, room)){
            Calendar start = Calendar.getInstance();
            start.setTime(from);
            Calendar end = Calendar.getInstance();
            end.setTime(to);
            for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                room.booked.add(date);
            }
            return true;
        }
        return false;
    }
}
