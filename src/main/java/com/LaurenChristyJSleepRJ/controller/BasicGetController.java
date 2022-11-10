package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;
import com.LaurenChristyJSleepRJ.Algorithm;
import java.util.List;

@RestController
public interface BasicGetController <T extends Serializable> {
    @GetMapping("/page")
    default List<T> getPage(@RequestParam int page, @RequestParam int pageSize){
        return Algorithm.paginate(getJsonTable(), page, pageSize, pred->true);
    }
    @GetMapping("/{id}")
    @SuppressWarnings("UnnecessaryLocalVariable")
    default T getById (@PathVariable int id){
        T obj = Algorithm.<T>find(getJsonTable(), pred->pred.id == id);
        return obj;
    }

    default JsonTable<T> getJsonTable(){
        return null;
    }
}