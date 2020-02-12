package org.com.sda.rest;

import org.com.sda.dto.TripDTO;
import org.com.sda.dto.TripDetailsDTO;
import org.com.sda.logic.service.TripDetailsService;
import org.com.sda.logic.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private TripDetailsService tripDetailsService;

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
    @PostMapping("/buyTrip")
    private ResponseEntity buyTrip(@RequestBody TripDetailsDTO tripDetailsDTO){

        return new ResponseEntity(tripDetailsService.buyTrip(tripDetailsDTO), HttpStatus.OK);
    }
    @GetMapping("/getPromotedTrips/{isPromoted}")
    private ResponseEntity getPromotedTrips(@PathVariable boolean isPromoted){
        List<TripDTO> tripDTOList = tripService.getPromotedTrips(isPromoted);
        if (tripDTOList!=null){
            return new ResponseEntity(tripDTOList,HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/upcomingTripsGlobally")
    private ResponseEntity upcomingTripsGlobally(){
        List<TripDTO> listOfUpcomingTripsGlobally = tripService.upcomingTripsGlobally();
        if(listOfUpcomingTripsGlobally.size() != 0 ){
            return new ResponseEntity(listOfUpcomingTripsGlobally,HttpStatus.OK);
        } else return ResponseEntity.noContent().build();
    }
//    @GetMapping("/sortTripByPrice")
}
