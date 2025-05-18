package com.application.service;

import com.application.model.Discount;
import com.application.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    Iterable<Discount> findAll();

    Discount save(Discount discount);

    void remove(long id);
//    Iterable<Discount> filterRoomType(String roomType);

    Optional<Discount> findDiscountById(long id);

    Discount findByType(String roomType);
}
