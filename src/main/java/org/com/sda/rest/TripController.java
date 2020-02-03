package org.com.sda.rest;

import org.com.sda.dto.TripDTO;
import org.com.sda.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @PutMapping("/addTrip")
    private ResponseEntity addTrip(@RequestBody TripDTO tripDTO){
        tripService.insertTrip(tripDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchTrip")
    private List<TripDTO> searchTrip(@RequestBody TripDTO tripDTO){
        List<TripDTO> tripDTOList = tripService.searchTrip(tripDTO);

        return ResponseEntity.ok(tripDTOList).getBody();
    }
}
