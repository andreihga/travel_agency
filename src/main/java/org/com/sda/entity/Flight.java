package org.com.sda.entity;

import javax.persistence.*;
import java.sql.Date;
@NamedQueries({
        @NamedQuery(name = "getFlightByFlightNumber", query = "select f from Flight f where f.flightNumber=:flightNumber"),
        @NamedQuery(name = "getFlightByDepartureDate", query = "select f from Flight f where f.departureDate = :departureDate"),
        @NamedQuery(name = "updateSeats", query = "update Flight as f set f.availableSeats=:updateAvailableSeats where f.airport=:airport")
})
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    private int flight_id;
    @Column(name = "flight_number")
    private String flightNumber;
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "total_number_seats")
    private int totalNumberSeats;
    @Column(name = "available_seats")
    private int availableSeats;
    @Column(name = "flight_price")
    private double flightPrice;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;


    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

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

    public int getTotalNumberSeats() {
        return totalNumberSeats;
    }

    public void setTotalNumberSeats(int totalNumberSeats) {
        this.totalNumberSeats = totalNumberSeats;
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

}
