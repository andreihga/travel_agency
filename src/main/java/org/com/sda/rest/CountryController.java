package org.com.sda.rest;

import org.com.sda.dto.CountryDTO;
import org.com.sda.logic.readers.CountryReader;
import org.com.sda.logic.service.ContinentService;
import org.com.sda.logic.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private ContinentService continentService;
    @Autowired
    private CountryReader countryReader;

    @GetMapping("/getCountries")
    public ResponseEntity countryDTOList(){
        List<CountryDTO> countryDTOList = countryService.getCountries();
        return ResponseEntity.ok(countryDTOList);
    }

    @PostMapping(value = "/addCountries", consumes = "application/json")
    public ResponseEntity addCountries(@RequestBody CountryDTO countryDTO){
        countryService.addCountries(countryDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addCountriesViaExcel")
    public ResponseEntity addCountriesViaExcel(){
        countryReader.readCountryFromFile();
        return ResponseEntity.ok().build();
    }
}

