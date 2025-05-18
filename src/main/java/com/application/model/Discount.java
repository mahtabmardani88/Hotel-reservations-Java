package com.application.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Discount {

    enum roomType {Single, Double, PentHouse}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long discountId;
    int percent_of_Discount;
    LocalDate startDate;
    LocalDate endDate;
    String type;


//@OneToOne
//Room room;



    public Discount(){

    }

    public Discount( int percent_of_Discount, LocalDate startDate, LocalDate endDate, String type ) {
        this.percent_of_Discount = percent_of_Discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }


    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public int getPercent_of_Discount() {
        return percent_of_Discount;
    }

    public void setPercent_of_Discount(int percent_of_Discount) {
        this.percent_of_Discount = percent_of_Discount;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}