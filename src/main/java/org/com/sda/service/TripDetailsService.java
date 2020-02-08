package org.com.sda.service;

import org.com.sda.Exceptions.RoomsUnavailable;
import org.com.sda.dto.TripDetailsDTO;
import org.com.sda.entity.Trip;
import org.com.sda.entity.TripDetails;
import org.com.sda.entity.User;
import org.com.sda.repository.TripDetailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String buyTrip(TripDetailsDTO tripDetailsDTO) {
        TripDetails tripDetails = new TripDetails();
        Trip trip = tripService.findTrip(tripDetailsDTO.getTrip());
        User user = userService.logIn(tripDetailsDTO.getUser());
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
        Double totalPrice = roomAvailabilityService.totalRoomsPrice(trip,tripDetailsDTO.getSingleRooms(), tripDetailsDTO.getDoubleRooms(), tripDetailsDTO.getExtraBed());
        Double discountedPrice = roomAvailabilityService.applyDiscount(totalPrice,user.getTotalAmount());
        tripDetails.setAmount(discountedPrice);
        return tripDetailsDAO.buyTrip(tripDetails);
    }
}
