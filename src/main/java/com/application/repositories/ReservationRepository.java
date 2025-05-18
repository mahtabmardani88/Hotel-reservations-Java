package com.application.repositories;

import com.application.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Iterable<Reservation> findReservationByEndDateAfterAndStartDateBefore(LocalDate startDate, LocalDate endDate);

}