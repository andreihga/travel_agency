package org.com.sda.dto;

public class AirportDTO {
    private String airportNameDTO;

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
