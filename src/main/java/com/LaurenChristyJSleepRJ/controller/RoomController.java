package com.LaurenChristyJSleepRJ.controller;

import java.util.ArrayList;
import java.util.List;
import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/room")
public class RoomController implements BasicGetController<Room> {

    @JsonAutowired(value = Room.class, filepath = "src/json/room.json")
    public static JsonTable<Room> roomTable;
    @Override
    public JsonTable<Room> getJsonTable() {
        return roomTable;
    }

    @GetMapping("/getAllRoom")
    List<Room> getAllRoom
            (
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "12") int pageSize
            )
    {
        return Algorithm.paginate(getJsonTable(), page, pageSize, r -> true);
    }

    @GetMapping("/{id}/renter")
    List<Room> getRoomRenter
            (
                    @PathVariable int id,
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "12") int pageSize
            )
    {
        return Algorithm.paginate(getJsonTable(), page, pageSize, room -> room.accountId == id);
    }

    @PostMapping("/create")
    public Room create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int size,
            @RequestParam int price,
            @RequestParam ArrayList<Facility> facility,
            @RequestParam City city,
            @RequestParam String address,
            @RequestParam BedType bedType
    )
    {
        if (Algorithm.<Account>exists(AccountController.accountTable, acc -> acc.id == accountId && acc.renter != null)) {
            Room newRoom = new Room(accountId, name, size, new Price(price), facility, city, address, bedType);
            roomTable.add(newRoom);
            return newRoom;
        }
        return null;
    }
}
