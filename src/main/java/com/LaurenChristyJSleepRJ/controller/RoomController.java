package com.LaurenChristyJSleepRJ.controller;

import java.util.ArrayList;
import java.util.List;
import com.LaurenChristyJSleepRJ.*;
import com.LaurenChristyJSleepRJ.dbjson.*;
import org.springframework.web.bind.annotation.*;

/**
 * A REST controller that provides methods to create, get room by renter, get all room, and filter the room list.
 *
 * <p>It implements the {@link BasicGetController} interface and uses a JsonTable of Room objects to store
 * room information.</p>
 *
 * @author Lauren Christy Tanudjaja
 * @see Room
 */
@RestController

@RequestMapping("/room")
public class RoomController implements BasicGetController<Room> {

    /**
     * A `JsonTable` that stores room data.
     *
     * @JsonAutowired indicates that this field should be initialized with data from the specified file.
     */
    @JsonAutowired(value = Room.class, filepath = "src/json/room.json")
    public static JsonTable<Room> roomTable;
    @Override
    public JsonTable<Room> getJsonTable() {
        return roomTable;
    }

    /**
     * This method is used to get all room.
     * <p>This method uses the paginate method from the Algorithm class to paginate the results.</p>
     *
     * @param page The page number to be return.
     * @param pageSize The number of room objects to return on each page.
     * @return All room that already created.
     */
    @GetMapping("/getAllRoom")
    List<Room> getAllRoom
            (
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "12") int pageSize
            )
    {
        return Algorithm.paginate(getJsonTable(), page, pageSize, r -> true);
    }

    /**
     * This method is used to get all room by renter.
     * <p>This method uses the paginate method from the Algorithm class to paginate the results.</p>
     *
     * @param id The ID of the room.
     * @param page The page number to be return.
     * @param pageSize The number of room objects to return on each page.
     * @return The room for the specified renter ID.
     */
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

    /**
     * Create a new room to the system using the accountId, name, size, price, facility, city, address, & bed type.
     *
     * @param accountId The ID the account wants to create room.
     * @param name The name of the room the account wants to create.
     * @param size The size of the room.
     * @param price The price of the room.
     * @param facility The facilities provided with the room.
     * @param city The city the room located to.
     * @param address The address of the room.
     * @param bedType The bed type in the room.
     * @return The created `Room` object if the creation is successful, or `null` otherwise.
     */
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
