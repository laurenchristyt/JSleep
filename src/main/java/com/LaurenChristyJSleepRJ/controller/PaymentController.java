package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.Payment;
import com.LaurenChristyJSleepRJ.dbjson.JsonAutowired;
import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PaymentController implements BasicGetController {
    @JsonAutowired(filepath=".scr/main/java/com/test.json", value= Payment.class)
    public static JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/accept")
    public boolean accept(int id){
        return true;
    }

    @PostMapping("/cancel")
    public boolean cancel(int id){
        return true;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int renterId, @RequestParam int roomId,@RequestParam String from, @RequestParam String to){
        return null;
    }

    @PostMapping("/submit")
    public boolean submit(int id, String receipt){
        return true;
    }

}