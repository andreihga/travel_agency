package org.com.sda.logic.service;

import org.com.sda.Exceptions.SeatsUnavailable;
import org.com.sda.dto.FlightDTO;
import org.com.sda.entity.Flight;
import org.com.sda.entity.Trip;
import org.com.sda.logic.consts.Consts;
import org.com.sda.logic.service.AirportService;
import org.com.sda.repository.FlightDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightDAO flightDAO;

    public void insertFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setTotalNumberSeats(flightDTO.getTotalNumberOfSeats());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setFlightPrice(flightDTO.getFlightPrice());
        flight.setAirport(airportService.getAirportFromAirportDTO(flightDTO.getAirportDTO()));
        flightDAO.addFlight(flight);
    }

    public Flight getFlightFromFlightDTOByDepartureDate(FlightDTO flightDTO) {
        if (flightDTO == null) {
            return null;
        } else {
            Flight flight = flightDAO.getFlightByDepartureDate(flightDTO.getDepartureDate());
            return flight;
        }
    }


    public Flight getFlightsByFlightDTO(FlightDTO flightDTO) {
        Flight flight = flightDAO.getFlightByFlightNumber(flightDTO.getFlightNumber());
        return flight;
    }

    public FlightDTO getFlightDTOFromFlight(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setTotalNumberOfSeats(flight.getTotalNumberSeats());
        flightDTO.setAvailableSeats(flight.getAvailableSeats());
        flightDTO.setFlightPrice(flight.getFlightPrice());
        flightDTO.setAirportDTO(airportService.getAirportDTOFromAirport(flight.getAirport()));
        return flightDTO;
    }

    public void checkSeats(Trip trip, int nrOfPersons) throws SeatsUnavailable {
        Flight flight = flightDAO.getFlightByDepartureDate(trip.getDepartureFlightTrip().getDepartureDate());
        if (flight.getAvailableSeats() >= nrOfPersons) {
            return;
        }
        throw new SeatsUnavailable(Consts.NO_AVAILABLE_SEATS);
    }

    public Double flightPrice(Trip trip, int nrOfPersons){
        Flight flight = flightDAO.getFlightByDepartureDate(trip.getDepartureFlightTrip().getDepartureDate());
        Double flightPrice = nrOfPersons * flight.getFlightPrice();
        return flightPrice;
    }

}
