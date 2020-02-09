package org.com.sda.logic.service;

import org.com.sda.dto.CityDTO;
import org.com.sda.entity.City;
import org.com.sda.repository.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityDAO cityDAO;

    public void addCity(CityDTO cityDTO) {
        City city = new City();
        city.setCityName(cityDTO.getCityName());
        city.setCountry(countryService.getCountriesByName(cityDTO.getCountryDTO()));
        cityDAO.addCity(city);
    }

    public City getCityFromCityDTO(CityDTO cityDTO) {
        City city = cityDAO.findCityByNameAndCountry(cityDTO.getCityName(),countryService.getCountriesByName(cityDTO.getCountryDTO()));
        return city;
    }

    public CityDTO getCityDTOFromCity(City city){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName(city.getCityName());
        cityDTO.setCountryDTO(countryService.getCountryDTOFromCountry(city.getCountry()));
        return cityDTO;
    }
}
