package com.application.controller;

import com.application.model.Guest;
import com.application.model.Reservation;
import com.application.model.Room;
import com.application.service.GuestService;
import com.application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GuestController {
    @Autowired
    GuestService guestService;

    // Endpoint
    // http://localhost:9191/api/guest
    // GET
    @GetMapping(value = "guest", produces = "application/json")
    public Iterable<Guest> getAllGuest() {

        return guestService.findAll();

    }


    // Endpoint
    // http://localhost:9191/api/guest
    // POST
    @PostMapping(value = "guest", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest){

        return ResponseEntity.ok().body(guestService.save(guest));

    }

    // Endpoint
    // http://localhost:9191/api/guest/2
    // DEL
    @DeleteMapping("guest/{id}")
    public ResponseEntity<Void> deleteGuestById( @PathVariable long id){

        guestService.remove(id);
        return ResponseEntity.ok().build();
    }

    // Endpoint
    // http://localhost:9191/api/guest/2
    // GET
    @GetMapping(value = "guest/{id}", produces = "application/json")
    public ResponseEntity<Guest> geGuestById(@PathVariable long id){
        Optional<Guest> guest = guestService.findGuestById(id);

        if(guest.isPresent()){
            return ResponseEntity.ok().body(guest.get());
        }

        return ResponseEntity.notFound().build();

    }

}




