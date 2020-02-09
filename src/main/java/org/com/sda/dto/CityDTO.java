package org.com.sda.dto;

import org.com.sda.entity.Country;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CityDTO {
    @NotNull
    @NotEmpty
    private String cityName;
    @NotNull @NotEmpty
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
