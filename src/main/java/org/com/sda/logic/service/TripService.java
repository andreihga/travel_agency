package org.com.sda.logic.service;

import org.com.sda.dto.TripDTO;
import org.com.sda.entity.City;
import org.com.sda.entity.Hotel;
import org.com.sda.entity.RoomAvailability;
import org.com.sda.entity.Trip;
import org.com.sda.logic.service.CityService;
import org.com.sda.logic.service.FlightService;
import org.com.sda.logic.service.HotelService;
import org.com.sda.repository.RoomAvailabilityDAO;
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
    @Autowired
    private RoomAvailabilityDAO roomAvailabilityDAO;

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

    public Trip findTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setDepartureFlightTrip(flightService.getFlightsByFlightDTO(tripDTO.getDepartureFlightTripDTO()));
        trip.setReturnFlightTrip(flightService.getFlightsByFlightDTO(tripDTO.getReturnFlightTripDTO()));
        trip.setHotelTrip(hotelService.getHotelByHotelDTO(tripDTO.getHotelTripDTO()));
        return tripDAO.findTrip(trip);
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
        } catch (NoResultException ex) {
            System.out.println("No hotel found!");
            city = cityService.getCityFromCityDTO(tripDTO.getHotelTripDTO().getCityDTO());
        }
        return tripDAO.searchTrip(trip, city);
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
    public List<TripDTO> getPromotedTrips(boolean isPromoted){
        List<Trip> tripList = tripDAO.getPromotedTrips(isPromoted);
        List<TripDTO> tripDTOList = new LinkedList<>();
        List<TripDTO> tripDTOList1 = getTripDTOS(tripList,tripDTOList);
        return tripDTOList;
    }

    private List<TripDTO> getTripDTOS(List<Trip> tripList,List<TripDTO> tripDTOList) {

        for (Trip t : tripList) {
            TripDTO tripDTO = new TripDTO();
            tripDTO.setDepartureFlightTripDTO(flightService.getFlightDTOFromFlight(t.getDepartureFlightTrip()));
            tripDTO.setReturnFlightTripDTO(flightService.getFlightDTOFromFlight(t.getReturnFlightTrip()));
            tripDTO.setHotelTripDTO(hotelService.getHotelDTOfromHotel(t.getHotelTrip()));
            tripDTO.setDepartureDateHotel(t.getDepartureDateHotel());
            tripDTO.setReturnDateHotel(t.getReturnDateHotel());
            tripDTO.setNrOfPersons(1);
            tripDTO.setPromoted(t.isPromoted());
            RoomAvailability room = roomAvailabilityDAO.seeAvailability(t);
            tripDTO.setNrExtraBeds(room.getNumberOfExtraBedsAvailable());
            tripDTOList.add(tripDTO);
        }
        return tripDTOList;
    }

    public List<TripDTO> upcomingTripsGlobally(){
        List<Trip> tripList = tripDAO.upcomingTripsGlobally();
        List<TripDTO> tripDTOList = new LinkedList<>();
        List<TripDTO> tripDTOList1 = getTripDTOS(tripList,tripDTOList);

        return tripDTOList1;
    }

//    public List<TripDTO> searchTripByPrice(TripDTO tripDTO) {
//        double smth;
//        List<TripDTO> tripDTOList = searchTrip(tripDTO);
//        List<TripDTO> tripDTOList1 = new LinkedList<>();
//        for (TripDTO t : tripDTOList) {
//            double departureFlightPrice = t.getDepartureFlightTripDTO().getFlightPrice() * tripDTO.getNrOfPersons();
//            double returnFlightPrice = t.getReturnFlightTripDTO().getFlightPrice() * tripDTO.getNrOfPersons();
//            RoomAvailability r = roomAvailabilityDAO.seeAvailability(findTrip(t));
//            smth = departureFlightPrice + returnFlightPrice + (r.getPriceDoubleRoom() * t.getNrDoubleRooms())
//                    + (r.getPriceSingleRoom() * t.getNrSingleRooms()) + (r.getPriceExtraBed() * t.getNrExtraBeds());
//            t.setPricePerTripBySearchCriteria(smth);
//            tripDTOList1.add(t);
//        }
////        compareTo()
//    }

//    @Override
//    public int compareTo(TripDTO o) {
//        return ;
//    }
}