package com.LaurenChristyJSleepRJ.controller;

import java.util.*;
import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

/**
 * An interface that provides basic RESTful APIs for getting data from a `JsonTable` object.
 *
 * @author Lauren Christy Tanudjaja
 * @param <T> The type of the object that will be handled by the controller (e.g. Account, Room, etc.)
 */
@RestController
@RequestMapping()
public interface BasicGetController<T extends Serializable> {

    /**
     * Returns a page of objects from the `JsonTable` object, starting from the specified page and with the specified
     * number of objects per page.
     *
     * @param page The index of the page to return.
     * @param pageSize The number of objects to return per page.
     * @return A list of objects from the `JsonTable` object.
     */
    @GetMapping("/page")
    default List<T> getPage(@RequestParam int page, @RequestParam int pageSize)
    {
        return Algorithm.paginate(getJsonTable(), page, pageSize, object -> true);
    }
    /**
     * Returns the object with the specified ID.
     *
     * @param id The ID of the object to return.
     * @return The object with the specified ID, or `null` if no such object exists.
     */
    @GetMapping("/{id}")
    default T getById(@PathVariable int id)
    {
        Predicate<T> predicate = p -> p.id == id;
        return Algorithm.find(getJsonTable(),predicate);
    }


    JsonTable<T> getJsonTable();
}
