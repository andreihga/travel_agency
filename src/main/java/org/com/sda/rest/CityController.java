package org.com.sda.rest;

import org.com.sda.dto.CityDTO;
import org.com.sda.logic.readers.CityReader;
import org.com.sda.logic.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityReader cityReader;

    @PostMapping(value = "/addCities", consumes = "application/json")
    public ResponseEntity addCities(@RequestBody CityDTO cityDTO) {
        cityService.addCity(cityDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addCitiesViaExcel")
    private ResponseEntity addCitiesViaExcel() {
        cityReader.readCitiesFromFile();
        return ResponseEntity.ok().build();
    }
}
