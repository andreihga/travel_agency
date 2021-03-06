package org.com.sda.dto;

import org.com.sda.entity.Continent;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CountryDTO {
    @NotNull
    @NotEmpty
    private String countryName;
    @NotNull @NotEmpty
    private ContinentDTO continentDTO;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ContinentDTO getContinentDTO() {
        return continentDTO;
    }

    public void setContinentDTO(ContinentDTO continentDTO) {
        this.continentDTO = continentDTO;
    }
}
