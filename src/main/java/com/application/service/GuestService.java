package com.application.service;

import com.application.model.Guest;
import com.application.model.Reservation;
import com.application.model.Room;

import java.util.Optional;

public interface GuestService {
    Iterable<Guest> findAll();
    Guest save(Guest guest);
    void remove( long id);
    Optional<Guest> findGuestById(long id);
}
