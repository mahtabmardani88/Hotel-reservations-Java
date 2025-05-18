package com.application.service;
import com.application.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface RoomService {


    Iterable<Room> findAll();
    Optional<Room> findById(long id);
    Room save(Room room);
    void cleaned(long id);
    void remove( long id);
    Iterable<Room> filterRoomByType(String roomType);
//    public List<Room> getAvailableRooms(Room room, LocalDate startDate, LocalDate endDate);
    public Room findByRoomNumber(int roomNumber);

}
