package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.*;
import com.LaurenChristyJSleepRJ.Account;
import com.LaurenChristyJSleepRJ.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Account.class, filepath = "src/json/account.json")
    public static JsonTable<Payment> paymentTable;

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }


    @PostMapping("/submit")
    public boolean submit(@RequestParam int id) {return false;
    }
    @PostMapping("/cancel")
    public boolean cancel(@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(paymentTable, payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING) return false;
        else
        {
            Account buyer = Algorithm.<Account>find(AccountController.accountTable, account -> account.id == payment.buyerId);
            Room room = Algorithm.<Room>find(RoomController.roomTable, room1 -> room1.id == payment.getRoomId());
            payment.status = Invoice.PaymentStatus.FAILED;
            buyer.balance =  buyer.balance + room.price.price;
            return true;
        }
    }

    @PostMapping("/accept")
    public boolean accept(
            @PathVariable int id
    ) {
        Payment payment = Algorithm.<Payment>find(paymentTable, payment1 -> payment1.id == id);
        if(payment == null || payment.status != Invoice.PaymentStatus.WAITING)
            return false;
        else{
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return true;
        }
    }


}