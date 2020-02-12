package org.com.sda.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

public class AirportDTO {
    @NotNull @NotEmpty
    private String airportNameDTO;
    
    @NotNull @NotEmpty
    private CityDTO cityDTO;


    public String getAirportNameDTO() {
        return airportNameDTO;
    }

    public void setAirportNameDTO(String airportNameDTO) {
        this.airportNameDTO = airportNameDTO;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }
}