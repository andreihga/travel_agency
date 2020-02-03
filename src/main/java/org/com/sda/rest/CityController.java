package org.com.sda.rest;

import org.com.sda.dto.CityDTO;
import org.com.sda.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @PutMapping(value = "/addCities",consumes = "application/json")
    public ResponseEntity addCities(@RequestBody CityDTO cityDTO){
        cityService.addCity(cityDTO);

        return ResponseEntity.ok().build();
    }
}
