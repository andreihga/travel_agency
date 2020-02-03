package org.com.sda.rest;

import org.com.sda.dto.HotelDTO;
import org.com.sda.repository.HotelDAO;
import org.com.sda.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")

public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelDAO hotelDAO;

    @PutMapping(value = "/addHotel", consumes = "application/json")
    public ResponseEntity addHotel(@RequestBody HotelDTO hotelDTO){
        hotelService.addHotel(hotelDTO);

        return ResponseEntity.ok().build();
    }
}
