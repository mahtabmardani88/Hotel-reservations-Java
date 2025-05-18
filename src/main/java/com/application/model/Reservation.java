package com.application.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long reservationId;
    LocalDate startDate;
    LocalDate endDate;
    double Total_Price;
    boolean cancellation;

    @ManyToOne
    Guest guest;
    @ManyToOne
    Room room;
    @ManyToOne
    Discount discount;

    public Reservation() {

    }

    public Reservation( LocalDate startDate, LocalDate endDate,  Double total_Price, boolean cancellation, Guest guest, Room room, Discount discount) {
//        this.reservationId = reservation_id;

        this.startDate = startDate;
        this.endDate = endDate;
        this.Total_Price = total_Price;
        this.cancellation = cancellation;
        this.guest = guest;
        this.room = room ;
        this.discount = discount;
    }


    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
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

    public Double getTotal_Price() {
        return Total_Price;
    }

    public boolean isCancellation() {
        return cancellation;
    }

    public void setCancellation(boolean cancellation) {
        this.cancellation = cancellation;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double setTotal_Price(double total_Price) {
        Total_Price = total_Price;
        return total_Price;
    }
    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

}