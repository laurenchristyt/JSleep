package com.LaurenChristyJSleepRJ.controller;

import java.util.List;
import com.LaurenChristyJSleepRJ.Voucher;
import com.LaurenChristyJSleepRJ.Algorithm;
import com.LaurenChristyJSleepRJ.Price;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

/**
 * A REST controller that provides methods to provide available voucher, apply voucher, and used voucher.
 *
 * It implements the {@link BasicGetController} interface and uses a JsonTable of Voucher objects to store
 * voucher information.
 *
 * @author Lauren Christy Tanudjaja
 * @see Voucher
 */
@RestController
@RequestMapping("/voucher")
public class VoucherController implements BasicGetController<Voucher> {

    /**
     * A `JsonTable` that stores voucher data.
     *
     * @JsonAutowired indicates that this field should be initialized with data from the specified file.
     */
    @JsonAutowired(value = Voucher.class, filepath = "src/json/voucher.json")
    public static JsonTable<Voucher> voucherTable;

    /**
     * Returns the `JsonTable` object that stores the voucher.
     *
     * @return The `JsonTable` object that stores the voucher.
     */
    @Override
    public JsonTable<Voucher> getJsonTable()
    {
        return voucherTable;
    }

    /**
     * Retrieve a paginated list of available vouchers.
     *
     * @param page The page number to retrieve.
     * @param pageSize The number of vouchers per page.
     * @return A paginated list of available vouchers.
     */
    @GetMapping("/getAvailable")
    List<Voucher> getAvailable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int pageSize) {
        return Algorithm.paginate(getJsonTable(), page, pageSize, v -> !v.isUsed());
    }
    /**
     * Check if a voucher with the given ID can be applied to a given price.
     *
     * @param id The ID of the voucher to check.
     * @param price The price to check against.
     * @return `true` if the voucher can be applied to the given price, `false` otherwise.
     */
    @GetMapping("/{id}/canApply")
    boolean canApply(@PathVariable int id, @RequestParam double price) {
        return Algorithm.<Voucher>find(getJsonTable(), v -> v.id == id).canApply(new Price(price));
    }

    /**
     * Check if a voucher with the given ID has been used.
     *
     * @param id The ID of the voucher to check.
     * @return `true` if the voucher has been used, `false` otherwise.
     */
    @GetMapping("/{id}/isUsed")
    boolean isUsed(@PathVariable int id) {
        return Algorithm.<Voucher>find(getJsonTable(), v -> v.id == id).isUsed();
    }

}
