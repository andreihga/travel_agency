package org.com.sda.dto;

import java.sql.Date;

public class TripDTO {
    private FlightDTO departureFlightTripDTO;
    private FlightDTO returnFlightTripDTO;
    private HotelDTO hotelTripDTO;
    private Date departureDateHotel;
    private Date returnDateHotel;
    private boolean isPromoted;
    private int nrOfPersons;
    private int nrDoubleRooms;
    private int nrSingleRooms;

    public int getNrDoubleRooms() {
        return nrDoubleRooms;
    }

    public void setNrDoubleRooms(int nrDoubleRooms) {
        this.nrDoubleRooms = nrDoubleRooms;
    }

    public int getNrSingleRooms() {
        return nrSingleRooms;
    }

    public void setNrSingleRooms(int nrSingleRooms) {
        this.nrSingleRooms = nrSingleRooms;
    }

    public int getNrExtraBeds() {
        return nrExtraBeds;
    }

    public void setNrExtraBeds(int nrExtraBeds) {
        this.nrExtraBeds = nrExtraBeds;
    }

    private int nrExtraBeds;



    public int getNrOfPersons() {
        return nrOfPersons;
    }

    public void setNrOfPersons(int nrOfPersons) {
        this.nrOfPersons = nrOfPersons;
    }

    public FlightDTO getDepartureFlightTripDTO() {
        return departureFlightTripDTO;
    }

    public void setDepartureFlightTripDTO(FlightDTO departureFlightTripDTO) {
        this.departureFlightTripDTO = departureFlightTripDTO;
    }

    public FlightDTO getReturnFlightTripDTO() {
        return returnFlightTripDTO;
    }

    public void setReturnFlightTripDTO(FlightDTO returnFlightTripDTO) {
        this.returnFlightTripDTO = returnFlightTripDTO;
    }

    public HotelDTO getHotelTripDTO() {
        return hotelTripDTO;
    }

    public void setHotelTripDTO(HotelDTO hotelTripDTO) {
        this.hotelTripDTO = hotelTripDTO;
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
