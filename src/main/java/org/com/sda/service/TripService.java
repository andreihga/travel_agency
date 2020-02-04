package org.com.sda.service;

import org.com.sda.dto.HotelDTO;
import org.com.sda.dto.TripDTO;
import org.com.sda.entity.City;
import org.com.sda.entity.Hotel;
import org.com.sda.entity.Room;
import org.com.sda.entity.Trip;
import org.com.sda.repository.TripDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TripService {
    @Autowired
    private FlightService flightService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private TripDAO tripDAO;
    @Autowired
    private CityService cityService;

//    we convert tripDTO received from frontEnd into Trip, and then we will call the method from TripDAO to insert a trip into DB

    public void insertTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setDepartureFlightTrip(flightService.getFlightFromFlightDTO(tripDTO.getDepartureFlightTripDTO()));
        trip.setReturnFlightTrip(flightService.getFlightFromFlightDTO(tripDTO.getReturnFlightTripDTO()));
        trip.setHotelTrip(hotelService.getHotelByHotelDTO(tripDTO.getHotelTripDTO()));
        trip.setDepartureDateHotel(tripDTO.getDepartureDateHotel());
        trip.setReturnDateHotel(tripDTO.getReturnDateHotel());
        trip.setPromoted(tripDTO.isPromoted());
        tripDAO.addTrip(trip);
    }

    public List<Trip> getListOfTripsBySearchCriteria(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setDepartureFlightTrip(flightService.getFlightFromFlightDTO(tripDTO.getDepartureFlightTripDTO()));
        if (tripDTO.getReturnFlightTripDTO() == null) {
            trip.setReturnFlightTrip(null);
        } else {
            trip.setReturnFlightTrip(flightService.getFlightFromFlightDTO(tripDTO.getReturnFlightTripDTO()));
        }
//        daca sunt mai multe hoteluri in db, aici se opreste
            trip.setHotelTrip(hotelService.getHotelByHotelDTO(tripDTO.getHotelTripDTO()));
        return tripDAO.searchTrip(trip);
    }

    public List<TripDTO> searchTrip(TripDTO tripDTO) {
        List<Trip> tripsFoundByGivenTripDTO = getListOfTripsBySearchCriteria(tripDTO);
        List<TripDTO> tripDTOList = new LinkedList<>();
        for (Trip t : tripsFoundByGivenTripDTO) {
            TripDTO tripDTO1 = new TripDTO();
            tripDTO1.setDepartureFlightTripDTO(flightService.getFlightDTOFromFlight(t.getDepartureFlightTrip()));
            tripDTO1.setReturnFlightTripDTO(flightService.getFlightDTOFromFlight(t.getReturnFlightTrip()));
            Hotel hotel = t.getHotelTrip();
            Set<Room> roomSet = hotel.getRoomSet();
            Set<Room> roomsAvailableSet = new HashSet<>();
            for (Room r : roomSet) {
                if (tripDTO.getNrDoubleRooms() < r.getNumberDoubleRoomsAvailable() && tripDTO.getNrSingleRooms() < r.getNumberSingleRoomsAvailable() && tripDTO.getNrExtraBeds() < r.getNumberOfExtraBedsAvailable()) {
                    roomsAvailableSet.add(r);
                }
            }
            hotel.setRoomSet(roomsAvailableSet);
            tripDTO1.setHotelTripDTO(hotelService.getHotelDTOfromHotel(hotel));
            tripDTOList.add(tripDTO1);
        }
        return tripDTOList;
    }
}
