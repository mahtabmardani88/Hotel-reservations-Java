package com.application.service;

import com.application.model.Discount;
import com.application.model.Guest;
import com.application.model.Reservation;
import com.application.model.Room;
import com.application.repositories.ReservationRepository;
import com.application.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
//    @Autowired
//    ReservationService reservationService;

    @Override
    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(long id) {
        return reservationRepository.findById(id);
    }
    @Override
    public void cancelById(long id) {

        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setCancellation(true);

        reservationRepository.save(reservation);
    }

    @Override
    public Reservation save(Reservation reservation) {
        reservation.setTotal_Price(calculateTotalPriceWithDiscount(reservation));
        return reservationRepository.save(reservation);
    }

    @Override
    public void remove(long id) {
        reservationRepository.deleteById(id);

    }

    @Override
    public double calculateTotalPriceWithDiscount(Reservation reservation) {
        Room room = reservation.getRoom();
        Discount discount = reservation.getDiscount();
        LocalDate today = LocalDate.now();
        long daysUntilStart = ChronoUnit.DAYS.between(today, reservation.getStartDate());
        long numberOfDays = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
        double tPrice = room.getPrice() * numberOfDays;
        double tpPrice;
        double discountAmount = 0;

        if (reservation.isCancellation()) {
            if (daysUntilStart >= 14) {
                tpPrice = (tPrice / 100) * 75;
            } else if (daysUntilStart >= 2 && daysUntilStart <= 14) {
                tpPrice = (tPrice / 100) * 50;
            } else if (daysUntilStart == 1) {
                tpPrice = tPrice;
            } else {
                tpPrice = tPrice;
            }
            return tpPrice;
        }


        if (discount != null) {
            if (discount.getDiscountId() == 1 && room.getRoomType().equalsIgnoreCase("single")) {
                tPrice = (tPrice / 100) * (100 - discount.getPercent_of_Discount());
            } else if (discount.getDiscountId() == 2 && room.getRoomType().equalsIgnoreCase("double")) {
                tPrice = (tPrice / 100) * (100 - discount.getPercent_of_Discount());
            } else if (discount.getDiscountId() == 3 && room.getRoomType().equalsIgnoreCase("penthouse")) {
                tPrice = (tPrice / 100) * (100 - discount.getPercent_of_Discount());
            }
        }

//    tPrice -= discountAmount;
        return reservation.setTotal_Price(tPrice);
    }

    @Override
    public Iterable<Reservation> findReservationByEndDateAfterAndStartDateBefore(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findReservationByEndDateAfterAndStartDateBefore(startDate, endDate);
    }

    @Override
    public List<Room> getAvailableRooms(Room criteria, LocalDate startDate, LocalDate endDate) {

        Iterable<Reservation> reservations = reservationRepository.findReservationByEndDateAfterAndStartDateBefore(startDate, endDate);

        List<Long> roomIds = new ArrayList<>();

        for(Reservation reservation: reservations){
            roomIds.add(reservation.getRoom().getRoomId());
        }

        Iterable<Room> allRooms = roomRepository.findAll();

        List<Room> availableRooms = new ArrayList<>();

        for( Room room: allRooms){

            if(roomIds.contains(room.getRoomId() )){
                continue;
            }
            if( criteria.getAdults() > 0 && criteria.getAdults() > room.getAdults()){
                continue;
            }
            if( criteria.getChildren() > 0 && criteria.getChildren() > room.getChildren()){
                continue;
            }
            if( !criteria.getRoomType().equals(room.getRoomType())){
                continue;
            }

            availableRooms.add( room);
        }

        return availableRooms;

    }
//-------------------------------------------
    @Override
    public List<Reservation> getAvailableReservationsGuest(Guest guest, LocalDate startDate, LocalDate endDate) {
        return null;
    }
}