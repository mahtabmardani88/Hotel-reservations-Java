package com.application.repositories;

import com.application.model.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
    Guest findByEmailAndPassword(String email, String password);
}
