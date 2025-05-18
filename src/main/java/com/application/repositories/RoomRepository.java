package com.application.repositories;


import com.application.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
    Room findByRoomNumber(int roomNumber);
}