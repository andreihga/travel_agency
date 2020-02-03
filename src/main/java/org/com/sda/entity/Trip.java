package org.com.sda.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@NamedQueries({
        @NamedQuery(name = "searchTrip", query = "select t from Trip t where (:departureFlight = null or t.departureFlightId=:departureFlight)" +
                " and (:returnFlightId= null or t.returnFlightId =:returnFlightId)" +
                " and (:city = null or t.hotelId.city=:city)" +
                " and (:toHotel = null or t.hotelId=:toHotel)")
        //+
          //      " and (:nrOfPersonsOnThePlane = null or t.departureFlightId.availableSeats=:nrOfPersonsOnThePlane )")
})
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    private int trip_id;

    @OneToOne
    @JoinColumn(name = "departure_flight_id")
    private Flight departureFlightId;

    @OneToOne
    @JoinColumn(name = "return_flight_id")
    private Flight returnFlightId;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    @Column(name = "departure_date_hotel")
    private Date departureDateHotel;
    @Column(name = "return_date_hotel")
    private Date returnDateHotel;
    @Column(name = "promoted")
    private boolean isPromoted;

    public Flight getDepartureFlightId() {
        return departureFlightId;
    }

    public void setDepartureFlightId(Flight departureFlightId) {
        this.departureFlightId = departureFlightId;
    }

    public Flight getReturnFlightId() {
        return returnFlightId;
    }

    public void setReturnFlightId(Flight returnFlightId) {
        this.returnFlightId = returnFlightId;
    }

    public Hotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(Hotel hotelId) {
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
