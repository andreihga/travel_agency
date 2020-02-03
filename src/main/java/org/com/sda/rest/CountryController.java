package org.com.sda.rest;

import org.com.sda.dto.CountryDTO;
import org.com.sda.service.ContinentService;
import org.com.sda.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/getCountries")
    public ResponseEntity countryDTOList(){
        List<CountryDTO> countryDTOList = countryService.getCountries();
        return ResponseEntity.ok(countryDTOList);
    }

    @PutMapping(value = "/addCountries", consumes = "application/json")
    public ResponseEntity addCountries(@RequestBody CountryDTO countryDTO){
        countryService.addCountries(countryDTO);
        return ResponseEntity.ok().build();
    }

}

