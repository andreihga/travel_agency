package org.com.sda.dto;

import org.com.sda.entity.Country;

public class CityDTO {

    private String cityName;

    private CountryDTO countryDTO;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }
}
