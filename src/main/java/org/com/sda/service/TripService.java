package org.com.sda.service;

import org.com.sda.dto.TripDTO;
import org.com.sda.entity.Trip;
import org.com.sda.repository.TripDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private FlightService flightService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private TripDAO tripDAO;

    public void insertTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        tripDAO.addTrip(convertTripFromTripDTO(tripDTO,trip));
    }

    public Trip convertTripFromTripDTO(TripDTO tripDTO, Trip trip) {
        trip.setDepartureFlightId(flightService.getFlightFromFlightDTO(tripDTO.getDepartureFlightId()));
        if(flightService.getFlightFromFlightDTO(tripDTO.getReturnFlightId())==null){
            trip.setReturnFlightId(null);
        }else {
        trip.setReturnFlightId(flightService.getFlightFromFlightDTO(tripDTO.getReturnFlightId()));
        }
        trip.setHotelId(hotelService.getHotelByHotelDTO(tripDTO.getHotelId()));
        trip.setDepartureDateHotel(tripDTO.getDepartureDateHotel());
        trip.setReturnDateHotel(tripDTO.getReturnDateHotel());
        trip.setPromoted(tripDTO.isPromoted());
        return trip;
    }

    public List<TripDTO> searchTrip(TripDTO tripDTO){
        Trip tripToSearch = new Trip();
        Trip trip = convertTripFromTripDTO(tripDTO,tripToSearch);
        List<Trip> tripList = tripDAO.searchTrip(trip);
        List<TripDTO> tripDTOList = new ArrayList<>();
        for (Trip t : tripList) {
            TripDTO tripDTO1 = new TripDTO();
            tripDTO1.setDepartureFlightId(flightService.getFlightDTOFromFlight(t.getDepartureFlightId()));
            if(flightService.getFlightDTOFromFlight(t.getReturnFlightId()) == null){
                tripDTO1.setReturnFlightId(null);
            } else {
                tripDTO1.setReturnFlightId(flightService.getFlightDTOFromFlight(t.getReturnFlightId()));
            }
            tripDTO1.setHotelId(hotelService.getHotelDTOfromHotel(t.getHotelId()));
            tripDTOList.add(tripDTO1);
        }
        return tripDTOList;
    }
}
