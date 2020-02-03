package org.com.sda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "trip_details")
public class TripDetails {
    @Id
    private int tripDetailsId;
    @Column(name = "trip_id")
    private int tripId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "single_rooms")
    private int singleRooms;
    @Column(name = "double_rooms")
    private int doubleRooms;
    @Column(name = "extra_bed")
    private int extraBed;
    @Column(name = "amount")
    private double amount;

    @Override
    public String toString() {
        return "TripDetails{" +
                "tripDetailsId=" + tripDetailsId +
                ", tripId=" + tripId +
                ", userId=" + userId +
                ", singleRooms=" + singleRooms +
                ", doubleRooms=" + doubleRooms +
                ", extraBed=" + extraBed +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDetails that = (TripDetails) o;
        return tripDetailsId == that.tripDetailsId &&
                tripId == that.tripId &&
                userId == that.userId &&
                singleRooms == that.singleRooms &&
                doubleRooms == that.doubleRooms &&
                extraBed == that.extraBed &&
                Double.compare(that.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripDetailsId, tripId, userId, singleRooms, doubleRooms, extraBed, amount);
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
