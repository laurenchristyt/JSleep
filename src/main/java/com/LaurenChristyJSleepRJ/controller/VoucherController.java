package com.LaurenChristyJSleepRJ.controller;

import java.util.List;
import com.LaurenChristyJSleepRJ.Voucher;
import com.LaurenChristyJSleepRJ.Algorithm;
import com.LaurenChristyJSleepRJ.Price;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher")
public class VoucherController implements BasicGetController<Voucher> {

    @JsonAutowired(value = Voucher.class, filepath = "src/json/voucher.json")
    public static JsonTable<Voucher> voucherTable;

    @Override
    public JsonTable<Voucher> getJsonTable()
    {
        return voucherTable;
    }

    @GetMapping("/getAvailable")
    List<Voucher> getAvailable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int pageSize) {
        return Algorithm.paginate(getJsonTable(), page, pageSize, v -> !v.isUsed());
    }

    @GetMapping("/{id}/canApply")
    boolean canApply(@PathVariable int id, @RequestParam double price) {
        return Algorithm.<Voucher>find(getJsonTable(), v -> v.id == id).canApply(new Price(price));
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed(@PathVariable int id) {
        return Algorithm.<Voucher>find(getJsonTable(), v -> v.id == id).isUsed();
    }

}
