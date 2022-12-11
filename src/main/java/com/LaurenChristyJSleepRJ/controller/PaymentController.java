package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.JsonAutowired;
import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A REST controller that provides methods to create, accept, cancel, and submit payments.
 *
 * <p>This class implements the {@link BasicGetController} interface, which provides methods to
 * get a single object by ID and to get a page of objects from the `JsonTable Payment` that stores the data.</p>
 *
 * @author Lauren Christy Tanudjaja
 * @see Payment
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    /**
     * A `JsonTable` that stores payment data.
     *
     * @JsonAutowired indicates that this field should be initialized with data from the specified file.
     */
    @JsonAutowired
            (value = Payment.class, filepath = "src/json/payment.json")
    public static JsonTable<Payment> paymentTable;

    /**
     * Returns the `JsonTable` object that stores the payment data.
     *
     * @return The `JsonTable` object that stores the payment data.
     */
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/create")
    public Payment create(
            @RequestParam int buyerId,@RequestParam int renterId,
            @RequestParam int roomId, @RequestParam String from, @RequestParam String to
    ){
        Account cari = Algorithm.<Account>find(AccountController.accountTable,pred->pred.id==buyerId);
        Room cariruang = Algorithm.<Room>find(RoomController.roomTable, pred->pred.id==roomId);
        double hargaKamar = cariruang.price.price;
        System.out.println(from);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromtgl = null;
        Date totgl = null;
        try{
            fromtgl = sdf.parse(from);
            totgl = sdf.parse(to);
            System.out.println("date  "+totgl);
            // if(cari.balance>=hargaKamar&&Payment.availability(fromtgl, totgl, cariruang)){
            //     Payment baru = new Payment(buyerId,renterId,roomId,fromtgl,totgl);
            //     baru.status=Invoice.PaymentStatus.WAITING;
            //     Payment.makeBooking(fromtgl, totgl, cariruang);
            //     System.out.println("response backend: "+baru.toString());
            //     paymentTable.add(baru);
            //     return baru;
            // }
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        long diffInMilliseconds = totgl.getTime() - fromtgl.getTime();
        long diffInDays = diffInMilliseconds / (1000 * 60 * 60 * 24);

        System.out.println(cari.toString());
        if(cari.balance>=hargaKamar * diffInDays &&Payment.availability(fromtgl, totgl, cariruang)){
            System.out.println(cari.toString());
            Payment baru = new Payment(buyerId,renterId,roomId,fromtgl,totgl);
            baru.status=Invoice.PaymentStatus.WAITING;
            Payment.makeBooking(fromtgl, totgl, cariruang);
            cari.balance-=hargaKamar * (double) diffInDays;
            //apa ngurangin jumlahnya disini?
            System.out.println("response backend: "+baru.toString());
            paymentTable.add(baru);
            return baru;
        }
        if(cari.balance<hargaKamar){
            System.out.println("gapunya duit ya");
        }
        if(!Payment.availability(fromtgl, totgl, cariruang)){
            System.out.println("kamar sudah di booking");
        }
        return null;
        //return new Payment(buyerId,renterId,roomId,fromtgl,totgl);

    }
    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(paymentTable, pred -> pred.id == id);
        if (payment != null && payment.status == Invoice.PaymentStatus.WAITING) {
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return true;
        }
        return false;
    }
    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(paymentTable, pred -> pred.id == id);
        long diffInMilliseconds = payment.to.getTime() - payment.from.getTime();
        long diffInDays = diffInMilliseconds / (1000 * 60 * 60 * 24);
        if (payment != null && payment.status == Invoice.PaymentStatus.WAITING) {
            payment.status = Invoice.PaymentStatus.FAILED;
            Account acc = Algorithm.<Account>find(AccountController.accountTable, pred -> pred.id == payment.buyerId);
            Room room = Algorithm.<Room>find(RoomController.roomTable, pred -> pred.id == payment.renterId);
            acc.balance += diffInDays * room.price.price;
            return true;
        }
        return false;
    }

    @PostMapping("/{id}/rating")
    public boolean rating(@PathVariable int id, @RequestParam String rating) {
        Payment payment = Algorithm.<Payment>find(paymentTable, pred -> pred.id == id);

        if (payment != null && payment.status == Invoice.PaymentStatus.SUCCESS) {
            System.out.println("rating: "+rating);
            payment.rating = Invoice.RoomRating.valueOf(rating);
            return true;
        }
        return false;
    }

    @GetMapping("getAll/{id}")
    List<Payment> getAccountPayment(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
        return Algorithm.paginate(getJsonTable(), page, pageSize, pred -> pred.buyerId == id);
    }
}