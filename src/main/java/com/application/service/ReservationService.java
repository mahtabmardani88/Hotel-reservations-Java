package com.application.service;

import com.application.model.Guest;
import com.application.model.Reservation;
import com.application.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Iterable<Reservation> findAll();
    Optional<Reservation> findById(long id);
    void cancelById(long id);
    Reservation save(Reservation reservation);
    void remove( long id);
    double calculateTotalPriceWithDiscount(Reservation reservation);
    public Iterable<Reservation> findReservationByEndDateAfterAndStartDateBefore(LocalDate startDate, LocalDate endDate);
    public List<Room> getAvailableRooms(Room room, LocalDate startDate, LocalDate endDate);
//------------------------------------------------
    List<Reservation> getAvailableReservationsGuest(Guest guest, LocalDate startDate, LocalDate endDate);
}
