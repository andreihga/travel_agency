package org.com.sda.dto;

import org.com.sda.entity.Trip;
import org.com.sda.entity.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TripDetailsDTO {

    private TripDTO trip;

    private UserDTO user;

    private int singleRooms;

    private int doubleRooms;

    private int extraBed;

    private double amount;

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO tripDTO) {
        this.trip = tripDTO;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO userDTO) {
        this.user = userDTO;
    }

    public int getSingleRooms() {
        return singleRooms;
    }

    public void setSingleRooms(int singleRooms) {
        this.singleRooms = singleRooms;
    }

    public int getDoubleRooms() {
        return doubleRooms;
    }

    public void setDoubleRooms(int doubleRooms) {
        this.doubleRooms = doubleRooms;
    }

    public int getExtraBed() {
        return extraBed;
    }

    public void setExtraBed(int extraBed) {
        this.extraBed = extraBed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
