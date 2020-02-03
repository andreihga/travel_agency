package org.com.sda.service;

import org.com.sda.dto.AirportDTO;
import org.com.sda.entity.Airport;
import org.com.sda.repository.AirportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {
    @Autowired
    private AirportDAO airportDAO;
    @Autowired
    private CityService cityService;

    public void addAirport(AirportDTO airportDTO){
        Airport airport = new Airport();
        airport.setAirportName(airportDTO.getAirportNameDTO());
        airport.setCity(cityService.getCityFromCityDTO(airportDTO.getCityDTO()));

        airportDAO.addAirport(airport);
    }

    public List<AirportDTO> getAirports(){
        List<AirportDTO> airportDTOList = new ArrayList<>();
        List<Airport> airportList = airportDAO.getAirports();

        for (Airport a : airportList) {
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setAirportNameDTO(a.getAirportName());
            airportDTO.setCityDTO(cityService.getCityDTOFromCity(a.getCity()));
            airportDTOList.add(airportDTO);
        }
        return  airportDTOList;
    }

    public Airport getAirportFromAirportDTO(AirportDTO airportDTO){
        Airport airport = airportDAO.getAirportByNameAndCity(airportDTO.getAirportNameDTO(),cityService.getCityFromCityDTO(airportDTO.getCityDTO()));
        return airport;
    }

    public AirportDTO getAirportDTOFromAirport(Airport airport){
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setAirportNameDTO(airport.getAirportName());
        airportDTO.setCityDTO(cityService.getCityDTOFromCity(airport.getCity()));
        return airportDTO;
    }

}
