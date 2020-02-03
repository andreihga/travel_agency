package org.com.sda.dto;

import org.com.sda.entity.Continent;

public class CountryDTO {
    private String countryName;

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
