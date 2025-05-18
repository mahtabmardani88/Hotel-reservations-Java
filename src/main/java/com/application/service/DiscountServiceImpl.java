package com.application.service;


import com.application.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Discount;
import com.application.repositories.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    @Override
    public Iterable<Discount> findAll() {return discountRepository.findAll();
    }

    @Override
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public void remove(long type_id) {
        discountRepository.deleteById(type_id);
    }

    @Override
    public Optional<Discount> findDiscountById(long id) {
        return discountRepository.findById(id);
    }

    @Override
    public Discount findByType(String roomType) {
        return discountRepository.findByType( roomType);
    }

}