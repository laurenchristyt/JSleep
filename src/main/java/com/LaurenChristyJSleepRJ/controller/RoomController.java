package com.LaurenChristyJSleepRJ.controller;

import com.LaurenChristyJSleepRJ.dbjson.JsonAutowired;
import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.LaurenChristyJSleepRJ.Facility;
import com.LaurenChristyJSleepRJ.City;
import com.LaurenChristyJSleepRJ.Room;
import com.LaurenChristyJSleepRJ.dbjson.JsonAutowired;
import com.LaurenChristyJSleepRJ.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/room")

public class RoomController implements BasicGetController{

    @PostMapping("/create")
    public Room create(@RequestParam int accountId, @RequestParam String name, @RequestParam int size, @RequestParam int price, @RequestParam Facility facility, @RequestParam City city, @RequestParam String address) {
        return null;
    }

    @GetMapping("/{id}/renter")
    List<Room> getRoomByRenter(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
        return null;
    }

    @JsonAutowired(value = Room.class, filepath = "src/json/room.json")
    public static JsonTable<Room> roomTable;
    @Override
    public JsonTable<Room> getJsonTable() {
        return roomTable;
    }
}