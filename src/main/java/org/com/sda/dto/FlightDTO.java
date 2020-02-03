package org.com.sda.dto;

import java.util.Date;

public class FlightDTO {
    private String flightNumber;
    private Date departureDate;
    private int totalNumberOfSeats;
    private int availableSeats;
    private double flightPrice;
    private AirportDTO airportDTO;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    public void setTotalNumberOfSeats(int totalNumberOfSeats) {
        this.totalNumberOfSeats = totalNumberOfSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public AirportDTO getAirportDTO() {
        return airportDTO;
    }

    public void setAirportDTO(AirportDTO airportDTO) {
        this.airportDTO = airportDTO;
    }
}
