package org.com.sda.service;

import org.com.sda.dto.TripDTO;
import org.com.sda.entity.City;
import org.com.sda.entity.Hotel;
import org.com.sda.entity.RoomAvailability;
import org.com.sda.entity.Trip;
import org.com.sda.repository.TripDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
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
        trip.setDepartureFlightTrip(flightService.getFlightsByFlightDTO(tripDTO.getDepartureFlightTripDTO()));
        trip.setReturnFlightTrip(flightService.getFlightsByFlightDTO(tripDTO.getReturnFlightTripDTO()));
        trip.setHotelTrip(hotelService.getHotelByHotelDTO(tripDTO.getHotelTripDTO()));
        trip.setDepartureDateHotel(tripDTO.getDepartureDateHotel());
        trip.setReturnDateHotel(tripDTO.getReturnDateHotel());
        trip.setPromoted(tripDTO.isPromoted());
        tripDAO.addTrip(trip);
    }

    public List<Trip> getListOfTripsBySearchCriteria(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setDepartureFlightTrip(flightService.getFlightFromFlightDTOByDepartureDate(tripDTO.getDepartureFlightTripDTO()));
        if (tripDTO.getReturnFlightTripDTO() == null) {
            trip.setReturnFlightTrip(null);
        } else {
            trip.setReturnFlightTrip(flightService.getFlightFromFlightDTOByDepartureDate(tripDTO.getReturnFlightTripDTO()));
        }

        Hotel hotel = null;
        City city = null;
        try {
            hotel = hotelService.getHotelByHotelDTO(tripDTO.getHotelTripDTO());
            city = hotel.getCity();
            trip.setHotelTrip(hotel);
        } catch (NoResultException ex){
            System.out.println("No hotel found!");
            city = cityService.getCityFromCityDTO(tripDTO.getHotelTripDTO().getCityDTO());
        }
        return tripDAO.searchTrip(trip,city);
    }

    public List<TripDTO> searchTrip(TripDTO tripDTO) {
        List<Trip> tripsFoundByGivenTripDTO = getListOfTripsBySearchCriteria(tripDTO);
        List<TripDTO> tripDTOList = new LinkedList<>();
        for (Trip t : tripsFoundByGivenTripDTO) {
            TripDTO tripDTO1 = new TripDTO();
            tripDTO1.setDepartureFlightTripDTO(flightService.getFlightDTOFromFlight(t.getDepartureFlightTrip()));
            tripDTO1.setReturnFlightTripDTO(flightService.getFlightDTOFromFlight(t.getReturnFlightTrip()));
            Hotel hotel = t.getHotelTrip();
            Set<RoomAvailability> roomSet = hotel.getRoomSet();
            Set<RoomAvailability> roomsAvailableSet = new HashSet<>();
            for (RoomAvailability r : roomSet) {
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

    public Trip findTrip(TripDTO tripDTO){
        Trip trip = new Trip();
        trip.setDepartureFlightTrip(flightService.getFlightsByFlightDTO(tripDTO.getDepartureFlightTripDTO()));
        trip.setReturnFlightTrip(flightService.getFlightsByFlightDTO(tripDTO.getReturnFlightTripDTO()));
        trip.setHotelTrip(hotelService.getHotelByHotelDTO(tripDTO.getHotelTripDTO()));
        return tripDAO.findTrip(trip);
    }
}