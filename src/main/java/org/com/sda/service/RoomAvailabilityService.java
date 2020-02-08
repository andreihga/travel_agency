package org.com.sda.service;

import org.com.sda.Exceptions.RoomsUnavailable;
import org.com.sda.dto.RoomDTO;
import org.com.sda.entity.RoomAvailability;
import org.com.sda.entity.Trip;
import org.com.sda.repository.RoomAvailabilityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomAvailabilityService {
    @Autowired
    private RoomAvailabilityDAO roomAvailabilityDAO;
    @Autowired
    private HotelService hotelService;


    public void addRoom(RoomDTO roomDTO){
        RoomAvailability room = new RoomAvailability();
        room.setFromDate(roomDTO.getFromDate());
        room.setToDate(roomDTO.getToDate());
        room.setNumberDoubleRoomsAvailable(roomDTO.getNrOfAvailableDoubleRooms());
        room.setNumberSingleRoomsAvailable(roomDTO.getNrOfAvailableSingleRooms());
        room.setNumberOfExtraBedsAvailable(roomDTO.getNumberOfExtraBedsAvailable());
        room.setPriceDoubleRoom(roomDTO.getPriceDoubleRoom());
        room.setPriceSingleRoom(roomDTO.getPriceSingleRoom());
        room.setPriceExtraBed(roomDTO.getPriceExtraBed());
        room.setHotel(hotelService.getHotelByHotelDTO(roomDTO.getHotelDTO()));
        roomAvailabilityDAO.addRoom(room);
    }

    public void roomAvailability(Trip trip, int singleRoom, int doubleRoom, int extraBed) throws RoomsUnavailable {
        RoomAvailability roomsAvailability = roomAvailabilityDAO.seeAvailability(trip);
        if (roomsAvailability.getNumberSingleRoomsAvailable() >= singleRoom &&
                roomsAvailability.getNumberDoubleRoomsAvailable() >= doubleRoom &&
                roomsAvailability.getNumberOfExtraBedsAvailable() >= extraBed) {
            return;
        }
        throw new RoomsUnavailable("No rooms available");
    }

    public Double totalRoomsPrice(Trip trip, int singleRoom, int doubleRoom, int extraBed) {
        RoomAvailability room = roomAvailabilityDAO.seeAvailability(trip);
        return room.getPriceSingleRoom() * singleRoom + room.getPriceDoubleRoom() * doubleRoom + room.getPriceExtraBed() * extraBed;
    }


}

