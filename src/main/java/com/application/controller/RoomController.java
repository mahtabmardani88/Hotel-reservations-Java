package com.application.controller;

import com.application.model.Guest;
import com.application.model.Room;
import com.application.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api")
public class RoomController {

    protected static final Logger logger = LogManager.getLogger(RoomController.class);

    @Autowired
    RoomService roomService;


    // Endpoint
    // http://localhost:9191/api/room
    // GET
    @GetMapping(value = "room", produces = "application/json")
    public Iterable<Room> getAllRoom() {

        return roomService.findAll();

    }
    // Endpoint
    // http://localhost:9191/api/room/2
    // DEL
    @DeleteMapping("room/{id}")
    public ResponseEntity<Void> deleteRoomById( @PathVariable long id){

        roomService.remove(id);
        return ResponseEntity.ok().build();
    }

    // Endpoint
    // http://localhost:9191/api/room/2
    // GET
    @GetMapping(value = "room/{id}", produces = "application/json")
    public ResponseEntity<Object> getRoomById(@PathVariable long id) {
        Optional<Room> room = roomService.findById(id);

        if (room.isPresent()) {
            return ResponseEntity.ok().body(room.get());
        }

        return ResponseEntity.notFound().build();

    }
    // Endpoint
    // http://localhost:9191/api/room
    // POST
    @PostMapping(value = "room", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){

        return ResponseEntity.ok().body(roomService.save(room));

    }
    // Endpoint
    // http://localhost:9191/api/cleaned/2
    // Cancelled
    @GetMapping("cleaned/{id}")
    public ResponseEntity<Void> cleanedRoomById( @PathVariable long id){

        roomService.cleaned(id);
        return ResponseEntity.ok().build();
    }

    //     Endpoint
    //     http://localhost:9191/api/room/filter
    //     GET
    @GetMapping(value = "room/filter/{roomType}", produces = "application/json")
    public Iterable<Room> getByType_Id(@PathVariable String roomType) {
        return roomService.filterRoomByType(roomType);

    }

//    //     Endpoint
//    //     http://localhost:9191/api/room/available
//    //     POST
//    @PostMapping(value = "/room/available", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<List<Room>> getAvailableRooms(
//            @RequestBody final Room room,
//            @RequestParam("check-in-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam("check-out-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
//    ) {
//
//        logger.info("Inside 'getAvailableRooms'");
//
//        List<Room> rooms = roomService.getAvailableRooms(room, startDate, endDate);
//
//        return ResponseEntity.ok(rooms) ;
//
//    }
}