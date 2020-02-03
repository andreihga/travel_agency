package org.com.sda.rest;

import org.com.sda.dto.AirportDTO;
import org.com.sda.entity.Airport;
import org.com.sda.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @PutMapping(value = "/addAirport", consumes = "application/json")
    private ResponseEntity addAirport(@RequestBody AirportDTO airportDTO){
        airportService.addAirport(airportDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAirports")
    private ResponseEntity getAirports(){
        List<AirportDTO> airportDTOList = airportService.getAirports();

        return ResponseEntity.ok(airportDTOList);
    }

}
