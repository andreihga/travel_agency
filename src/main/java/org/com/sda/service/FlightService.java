package org.com.sda.service;

import org.com.sda.dto.AirportDTO;
import org.com.sda.dto.FlightDTO;
import org.com.sda.dto.TripDTO;
import org.com.sda.entity.Flight;
import org.com.sda.repository.FlightDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightDAO flightDAO;

    public void insertFlight(FlightDTO flightDTO){
        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setTotalNumberSeats(flightDTO.getTotalNumberOfSeats());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setFlightPrice(flightDTO.getFlightPrice());
        flight.setAirport(airportService.getAirportFromAirportDTO(flightDTO.getAirportDTO()));
        flightDAO.addFlight(flight);
    }

    public Flight getFlightFromFlightDTO(FlightDTO flightDTO){
        if(flightDTO == null){
            return null;
        } else {
            Flight flight = flightDAO.getFlightByDepartureDate(flightDTO.getDepartureDate());
            return flight;
        }
    }

    public Flight getFlightsByDepartureDate(TripDTO tripDTO){
        Flight flight = flightDAO.getFlightByDepartureDate(tripDTO.getDepartureDateHotel());
        return flight;
    }

    public FlightDTO getFlightDTOFromFlight(Flight flight){
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setTotalNumberOfSeats(flight.getTotalNumberSeats());
        flightDTO.setAvailableSeats(flight.getAvailableSeats());
        flightDTO.setFlightPrice(flight.getFlightPrice());
        flightDTO.setAirportDTO(airportService.getAirportDTOFromAirport(flight.getAirport()));

        return flightDTO;
    }
}
