package com.application.controller;

import com.application.model.Discount;
import com.application.model.Guest;
import com.application.model.Reservation;
import com.application.model.Room;
import com.application.service.DiscountService;
import com.application.service.GuestService;
import com.application.service.ReservationService;
import com.application.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



//import static sun.net.ftp.impl.FtpClient.logger;

@RestController
@RequestMapping("/api")
public class ReservationController {

    protected static final Logger logger = LogManager.getLogger(RoomController.class);

    @Autowired
    ReservationService reservationService;
    @Autowired
    DiscountService discountService;

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    // Endpoint
    // http://localhost:9191/api/reservation
    // GET
    @GetMapping(value = "reservation", produces = "application/json")
    public Iterable<Reservation> getAllReservation() {


        return reservationService.findAll();

    }
    // Endpoint
    // http://localhost:9191/api/reservation
    // POST
    @PostMapping(value = "reservation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        System.out.println("reservation = " + reservation);
        Room room = roomService.findByRoomNumber(reservation.getRoom().getRoomNumber());
        Discount discount = discountService.findByType( room.getRoomType());
        Guest guest = guestService.findGuestById( reservation.getGuest().getId()).get();


        reservation.setRoom(room);
        reservation.setDiscount(discount);
        reservation.setGuest(guest);
        reservation = reservationService.save(reservation);
        return ResponseEntity.ok().body(reservation);

    }




    // Endpoint
    // http://localhost:9191/api/reservation/2
    // GET
    @GetMapping(value = "reservation/{id}", produces = "application/json")
    public ResponseEntity<Reservation> getReservationById(@PathVariable long id){
        Optional<Reservation> reservation = reservationService.findById(id);

        if(reservation.isPresent()){
            return ResponseEntity.ok().body(reservation.get());
        }

        return ResponseEntity.notFound().build();

    }

    // Endpoint
    // http://localhost:9191/api/reservation/2
    // DEL
    @DeleteMapping("reservation/{id}")
    public ResponseEntity<Void> deleteReservationById( @PathVariable long id){

        reservationService.remove(id);
        return ResponseEntity.ok().build();
    }

    /////////cancellation
    // Endpoint
    // http://localhost:9191/api/cancellation/2
    // Cancelled
    @GetMapping("cancellation/{id}")
    public ResponseEntity<Void> CancelReservationById( @PathVariable long id){

        reservationService.cancelById(id);
        return ResponseEntity.ok().build();
    }

    //     Endpoint
    //     http://localhost:9191/api/calculateDiscountByTypeID
    //     Post
    @PostMapping(value = "reservation/calculateTotalPriceWithDiscount", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Double> calculateTotalPrice(@RequestBody Reservation reservation) {

        return ResponseEntity.ok().body(reservationService.calculateTotalPriceWithDiscount(reservation));

    }

    //     Endpoint
    //     http://localhost:9191/reservationincluded
    //     GET
@GetMapping(value = "/reservationincluded", produces = "application/json")
public ResponseEntity<Iterable<Reservation>> getReservationsIncluded(
        @RequestParam("check-in-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam("check-out-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){


    logger.info("Inside 'getReservationsIncluded'");

    try {
        Iterable<Reservation> reservations =
                reservationService.findReservationByEndDateAfterAndStartDateBefore(startDate, endDate);
        return ResponseEntity.ok( reservations);
    } catch (Exception e) {
        return ResponseEntity.ok( Collections.emptyList());
    }
}

    //     Endpoint
    //     http://localhost:9191/api/room/available
    //     POST
    @PostMapping(value = "/reservation/available", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Room>> getAvailableRooms(
            @RequestBody final Room room,
            @RequestParam("check-in-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("check-out-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {

        logger.info("Inside 'getAvailableRooms'");

        List<Room> rooms = reservationService.getAvailableRooms(room, startDate, endDate);

        return ResponseEntity.ok(rooms) ;

    }

//    ------------------------------
    // ...

//    @PostMapping(value = "/reservationguest", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Reservation> createReservationGuest(@RequestBody Reservation reservation) {
//        // Process the reservation and guest data
//        // ...
//
//        reservation = reservationService.save(reservation);
//        return ResponseEntity.ok().body(reservation);
//    }

    @PostMapping(value = "/reservationguest/available", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Reservation>> getAvailableReservationsGuest(
            @RequestBody Guest guest,
            @RequestParam("check-in-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("check-out-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<Reservation> reservations = reservationService.getAvailableReservationsGuest(guest, startDate, endDate);
        return ResponseEntity.ok(reservations);
    }

// ...


}
