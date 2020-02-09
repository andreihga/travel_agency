package org.com.sda.rest;

import org.com.sda.dto.FlightDTO;
import org.com.sda.logic.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PutMapping(value = "/addFlight")
    public ResponseEntity addFlight(@RequestBody FlightDTO flightDTO){

        flightService.insertFlight(flightDTO);

        return ResponseEntity.ok().build();
    }
}
