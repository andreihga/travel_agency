package org.com.sda.service;

import org.com.sda.dto.CityDTO;
import org.com.sda.dto.ContinentDTO;
import org.com.sda.dto.CountryDTO;
import org.com.sda.entity.City;
import org.com.sda.entity.Continent;
import org.com.sda.entity.Country;
import org.com.sda.repository.ContinentDAO;
import org.com.sda.repository.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private ContinentService continentService;

    public List<CountryDTO> getCountries() {
        List<Country> countryList = countryDAO.getCountries();
        List<CountryDTO> countryDTOList = new LinkedList<>();
        for (Country c : countryList) {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryName(c.getCountryName());
            countryDTO.setContinentDTO(continentService.getContinentDTO(c.getContinent().getContinent_name()));
            countryDTOList.add(countryDTO);
        }
        return countryDTOList;
    }

    public void addCountries(CountryDTO countryDTO){
        Country country = new Country();
        country.setCountryName(countryDTO.getCountryName());
        country.setContinent(continentService.getContinentByName(countryDTO.getContinentDTO()));
        countryDAO.addCountry(country);
    }

    public Country getCountriesByName(CountryDTO countryDTO){
        Country country = countryDAO.findCountryByName(countryDTO.getCountryName());
        return country;
    }
    public CountryDTO getCountryDTOFromCountry(Country country){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryName(country.getCountryName());
        countryDTO.setContinentDTO(continentService.getContinentDTO(country.getContinent().getContinent_name()));
        return countryDTO;
    }
}
