package org.com.sda.rest;

import org.com.sda.dto.HotelDTO;
import org.com.sda.logic.readers.HotelReader;
import org.com.sda.repository.HotelDAO;
import org.com.sda.logic.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")

public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelReader hotelReader;

    @PutMapping(value = "/addHotel", consumes = "application/json")
    public ResponseEntity addHotel(@RequestBody HotelDTO hotelDTO) {
        hotelService.addHotel(hotelDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addHotelViaExcel")
    private ResponseEntity addHotelViaExcel() {
        hotelReader.readHotelFromFile();
        return ResponseEntity.ok().build();
    }
}
