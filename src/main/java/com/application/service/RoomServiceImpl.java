package com.application.service;

import com.application.model.Discount;
import com.application.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.application.model.Room;
import com.application.repositories.RoomRepository;


@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationService reservationService;

    @Override
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {

        return roomRepository.save(room);
    }

    @Override
    public void cleaned(long id) {

        Room room = roomRepository.findById(id).get();
        room.setCleaned(true);

        roomRepository.save(room);
    }
    @Override
    public void remove(long id) {
        roomRepository.deleteById(id);

    }


    @Override
    public Iterable<Room> filterRoomByType(String roomType) {
        List<Room> rooms = (List<Room>) roomRepository.findAll();
        Iterable<Room> filtered = rooms.stream()
                .filter(x -> x.getRoomType().equals(roomType))
                .toList();

        return filtered;

    }

//    @Override
//    public List<Room> getAvailableRooms(Room criteria, LocalDate startDate, LocalDate endDate) {
//
//        Iterable<Reservation> reservations = reservationService.findReservationByEndDateAfterAndStartDateBefore(startDate, endDate);
//
//        List<Long> roomIds = new ArrayList<>();
//
//        for(Reservation reservation: reservations){
//            roomIds.add(reservation.getRoom().getRoomId());
//        }
//
//        Iterable<Room> allRooms = roomRepository.findAll();
//
//        List<Room> availableRooms = new ArrayList<>();
//
//        for( Room room: allRooms){
//
//            if(roomIds.contains(room.getRoomId() )){
//                continue;
//            }
//            if( criteria.getAdults() > 0 && criteria.getAdults() > room.getAdults()){
//                continue;
//            }
//            if( criteria.getChildren() > 0 && criteria.getChildren() > room.getChildren()){
//                continue;
//            }
//            if( !criteria.getRoomType().equals(room.getRoomType())){
//                continue;
//            }
//
//            availableRooms.add( room);
//        }
//
//        return availableRooms;
//
//    }

    @Override
    public Room findByRoomNumber(int roomNumber) {
        return roomRepository.findByRoomNumber( roomNumber);
    }

}