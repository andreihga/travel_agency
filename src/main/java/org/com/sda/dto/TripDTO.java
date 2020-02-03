package org.com.sda.dto;

import org.com.sda.entity.Flight;
import org.com.sda.entity.Hotel;

import java.util.Date;

public class TripDTO {
    private FlightDTO departureFlightId;
    private FlightDTO returnFlightId;
    private HotelDTO hotelId;
    private Date departureDateHotel;
    private Date returnDateHotel;
    private boolean isPromoted;

    public FlightDTO getDepartureFlightId() {
        return departureFlightId;
    }

    public void setDepartureFlightId(FlightDTO departureFlightId) {
        this.departureFlightId = departureFlightId;
    }

    public FlightDTO getReturnFlightId() {
        return returnFlightId;
    }

    public void setReturnFlightId(FlightDTO returnFlightId) {
        this.returnFlightId = returnFlightId;
    }

    public HotelDTO getHotelId() {
        return hotelId;
    }

    public void setHotelId(HotelDTO hotelId) {
        this.hotelId = hotelId;
    }

    public Date getDepartureDateHotel() {
        return departureDateHotel;
    }

    public void setDepartureDateHotel(Date departureDateHotel) {
        this.departureDateHotel = departureDateHotel;
    }

    public Date getReturnDateHotel() {
        return returnDateHotel;
    }

    public void setReturnDateHotel(Date returnDateHotel) {
        this.returnDateHotel = returnDateHotel;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }
}
