package com.application.repositories;

import com.application.model.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    Discount findByType(String roomType);
}
