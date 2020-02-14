package org.com.sda.rest;

import org.com.sda.dto.FlightDTO;
import org.com.sda.logic.readers.FlightsReader;
import org.com.sda.logic.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightsReader flightsReader;

    @PutMapping(value = "/addFlight")
    public ResponseEntity addFlight(@RequestBody FlightDTO flightDTO) {
        flightService.insertFlight(flightDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addFlightViaExcel")
    public ResponseEntity addFlightViaExcel() {
        flightsReader.readFlightsFromFile();
        return ResponseEntity.ok().build();
    }
}
