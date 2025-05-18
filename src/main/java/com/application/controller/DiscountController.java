package com.application.controller;

import com.application.model.Discount;
import com.application.model.Guest;
import com.application.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    // Endpoint
    // http://localhost:9191/api/discount
    // POST
    @PostMapping(value = "discount", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {


        return ResponseEntity.ok().body(discountService.save(discount));

    }

    // Endpoint
    // http://localhost:9191/api/discount
    // GET
    @GetMapping(value = "discount", produces = "application/json")
    public Iterable<Discount> getAllDiscount() {

        return discountService.findAll();

    }

    // Endpoint
    // http://localhost:9191/api/discount/2
    // DEL
    @DeleteMapping("discount/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable long id) {

        discountService.remove(id);
        return ResponseEntity.ok().build();
    }
    // Endpoint
    // http://localhost:9191/api/discount/2
    // GET
    @GetMapping(value = "discount/{id}", produces = "application/json")
    public ResponseEntity<Discount> geDiscountById(@PathVariable long id){
        Optional<Discount> discount = discountService.findDiscountById(id);

        if(discount.isPresent()){
            return ResponseEntity.ok().body(discount.get());
        }

        return ResponseEntity.notFound().build();

    }
}

