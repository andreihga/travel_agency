package org.com.sda.entity;

import javax.persistence.*;
import java.sql.Date;

@NamedQueries({
        @NamedQuery(name = "searchTrip", query = "select t from Trip t where (:departureFlight = null or t.departureFlightTrip=:departureFlight)" +
                " and (:returnFlightId= null or t.returnFlightTrip =:returnFlightId)" +
                " and (:city = null or t.hotelTrip.city=:city)" +
                " and (:toHotel = null or t.hotelTrip=:toHotel)"),
        @NamedQuery(name = "findTrip", query = "select t from Trip t where departureFlightTrip=:departureDate and returnFlightTrip=:returnDate and hotelTrip =:hotel"),
        @NamedQuery(name = "getPromotedTrips", query = "select t from Trip t where t.isPromoted=:isPromoted"),
        @NamedQuery(name = "upcomingTripsGlobally", query = "select t from Trip t where departureFlightTrip.departureDate > :currentDate"),
        @NamedQuery(name = "upcomingTripsByContinent", query = "select t from Trip t where departureFlightTrip.airport.city.country.continent.continent_name = :continent"),
        @NamedQuery(name = "upcomingTripsByCountry", query = "select t from Trip t where returnFlightTrip.airport.city.country.countryName = :country")

})
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    private int trip_id;

    @OneToOne
    @JoinColumn(name = "departure_flight_id")
    private Flight departureFlightTrip;

    @OneToOne
    @JoinColumn(name = "return_flight_id")
    private Flight returnFlightTrip;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelTrip;

    @Column(name = "departure_date_hotel")
    private Date departureDateHotel;
    @Column(name = "return_date_hotel")
    private Date returnDateHotel;
    @Column(name = "promoted")
    private boolean isPromoted;

    @Override
    public String toString() {
        return "Trip{" +
                "departureFlightTrip=" + departureFlightTrip +
                ", returnFlightTrip=" + returnFlightTrip +
                ", hotelTrip=" + hotelTrip +
                ", departureDateHotel=" + departureDateHotel +
                ", returnDateHotel=" + returnDateHotel +
                ", isPromoted=" + isPromoted +
                '}';
    }

    public Flight getDepartureFlightTrip() {
        return departureFlightTrip;
    }

    public void setDepartureFlightTrip(Flight departureFlightId) {
        this.departureFlightTrip = departureFlightId;
    }

    public Flight getReturnFlightTrip() {
        return returnFlightTrip;
    }

    public void setReturnFlightTrip(Flight returnFlightId) {
        this.returnFlightTrip = returnFlightId;
    }

    public Hotel getHotelTrip() {
        return hotelTrip;
    }

    public void setHotelTrip(Hotel hotelId) {
        this.hotelTrip = hotelId;
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
