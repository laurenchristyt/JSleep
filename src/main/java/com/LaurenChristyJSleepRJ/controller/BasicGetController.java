package com.LaurenChristyJSleepRJ.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import com.LaurenChristyJSleepRJ.dbjson.Serializable;

public interface BasicGetController<T extends Serializable> {
    abstract JsonTable<T> getJsonTable();
    @GetMapping("/page")
    public default List<T> getPage (@RequestParam int page, @RequestParam int pageSize){
        getJsonTable();
        return null;
    }
}