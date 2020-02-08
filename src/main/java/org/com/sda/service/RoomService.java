package org.com.sda.service;

import org.com.sda.dto.RoomDTO;
import org.com.sda.entity.RoomAvailability;
import org.com.sda.repository.RoomAvailabilityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomAvailabilityDAO roomDAO;

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
        roomDAO.addRoom(room);
    }
}
