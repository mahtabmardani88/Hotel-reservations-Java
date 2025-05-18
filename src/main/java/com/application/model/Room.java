package com.application.model;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long roomId;
    int roomNumber;
    String roomType;
    String floor;
    String bedType;
    int adults;
    int children;
    String comments;
    String facilities;
    double price;
    boolean cleaned;


    public Room() {
    }

    public Room(int roomNumber, String floor, String bedType, int adults, int children, String comments, String facilities, double price, boolean cleaned  ,String roomType ) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.bedType = bedType;
        this.adults = adults;
        this.children = children;
        this.comments = comments;
        this.facilities = facilities;
        this.price = price;
        this.cleaned = cleaned;
        this.roomType = roomType;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public double getPrice() {
        return price;
    }

    public double setPrice(double price) {
        this.price = price;
        return price;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
