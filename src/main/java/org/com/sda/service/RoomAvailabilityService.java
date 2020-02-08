package org.com.sda.service;

import org.com.sda.Exceptions.RoomsUnavailable;
import org.com.sda.entity.RoomAvailability;
import org.com.sda.entity.Trip;
import org.com.sda.repository.RoomAvailabilityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomAvailabilityService {
    @Autowired
    private RoomAvailabilityDAO roomAvailabilityDAO;

    public void roomAvailability(Trip trip, int singleRoom, int doubleRoom, int extraBed) throws RoomsUnavailable {
        RoomAvailability roomsAvailability = roomAvailabilityDAO.seeAvailability(trip);
        if (roomsAvailability.getNumberSingleRoomsAvailable() > singleRoom &&
                roomsAvailability.getNumberDoubleRoomsAvailable() > doubleRoom &&
                roomsAvailability.getNumberOfExtraBedsAvailable() > extraBed) {
            return;
        }
        throw new RoomsUnavailable("No rooms available");
    }

    public Double totalRoomsPrice(Trip trip, int singleRoom, int doubleRoom, int extraBed) {
        RoomAvailability room = roomAvailabilityDAO.seeAvailability(trip);
        return room.getPriceSingleRoom() * singleRoom + room.getPriceDoubleRoom() * doubleRoom + room.getPriceExtraBed() * extraBed;
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

