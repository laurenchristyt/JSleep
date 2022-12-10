package com.LaurenChristyJSleepRJ.controller;

import java.util.*;
import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public interface BasicGetController<T extends Serializable> {

    @GetMapping("/page")
    default List<T> getPage(@RequestParam int page, @RequestParam int pageSize)
    {
        return Algorithm.paginate(getJsonTable(), page, pageSize, object -> true);
    }
    @GetMapping("/{id}")
    default T getById(@PathVariable int id)
    {
        Predicate<T> predicate = p -> p.id == id;
        return Algorithm.find(getJsonTable(),predicate);
    }

    JsonTable<T> getJsonTable();
}
