package org.com.sda.logic.service;

import org.com.sda.Exceptions.RoomsUnavailable;
import org.com.sda.Exceptions.SeatsUnavailable;
import org.com.sda.dto.TripDetailsDTO;
import org.com.sda.entity.Trip;
import org.com.sda.entity.TripDetails;
import org.com.sda.entity.User;
import org.com.sda.repository.TripDetailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class TripDetailsService {
    @Autowired
    private TripService tripService;
    @Autowired
    private UserService userService;
    @Autowired
    private TripDetailsDAO tripDetailsDAO;
    @Autowired
    private RoomAvailabilityService roomAvailabilityService;
    @Autowired
    private FlightService flightService;

    public String buyTrip(TripDetailsDTO tripDetailsDTO) {
        TripDetails tripDetails = new TripDetails();
        Trip trip = tripService.findTrip(tripDetailsDTO.getTrip());
        User user = userService.logIn(tripDetailsDTO.getUser());
        try {
            flightService.checkSeats(trip, tripDetailsDTO.getNrOfPersons());
        } catch (SeatsUnavailable seatsUnavailable) {
            return seatsUnavailable.getMessage();
        }
        tripDetails.setTrip(trip);
        tripDetails.setUser(user);
        try {
            roomAvailabilityService.roomAvailability(trip, tripDetailsDTO.getSingleRooms(), tripDetailsDTO.getDoubleRooms(), tripDetailsDTO.getExtraBed());
        } catch (RoomsUnavailable ra) {
            return ra.getMessage();
        }
        tripDetails.setSingleRooms(tripDetailsDTO.getSingleRooms());
        tripDetails.setDoubleRooms(tripDetailsDTO.getDoubleRooms());
        tripDetails.setExtraBed(tripDetailsDTO.getExtraBed());
        Double totalPriceHotel = roomAvailabilityService.totalRoomsPrice(trip, tripDetailsDTO.getSingleRooms(), tripDetailsDTO.getDoubleRooms(), tripDetailsDTO.getExtraBed());
        Double totalPriceFlight = flightService.flightPrice(trip, tripDetailsDTO.getNrOfPersons());
        Double totalPrice = totalPriceFlight + totalPriceHotel;
        Double discountedPrice = applyDiscount(totalPrice, user.getTotalAmount());
        tripDetails.setAmount(discountedPrice);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        tripDetails.setTimestamp(timestamp);
        return tripDetailsDAO.buyTrip(tripDetails, tripDetailsDTO.getNrOfPersons());
    }

    public Double applyDiscount(double totalPrice, double userAmount) {
        if (userAmount < 600) {
            return totalPrice;
        } else if (userAmount >= 600 && userAmount < 1000) {
            return totalPrice - (totalPrice * 10 / 100);
        } else if (userAmount >= 1000 && userAmount < 2000) {
            return totalPrice - (totalPrice * 15 / 100);
        } else
            return totalPrice - (totalPrice * 20 / 100);
    }
}
