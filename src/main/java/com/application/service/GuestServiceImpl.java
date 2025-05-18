package com.application.service;

import com.application.model.Guest;
import com.application.model.Room;
import com.application.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GuestServiceImpl implements GuestService {


    @Autowired
    GuestRepository guestRepository;


    @Override
    public Guest save(Guest guest) {

        return guestRepository.save(guest);
    }
    @Override
    public void remove(long id) {
        guestRepository.deleteById(id);
    }
    @Override
    public Optional<Guest> findGuestById(long id) {
        return guestRepository.findById(id);
    }

    @Override
    public Iterable<Guest> findAll() {
        return guestRepository.findAll();
    }
}
