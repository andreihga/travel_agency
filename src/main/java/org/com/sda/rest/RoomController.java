package org.com.sda.rest;

import org.com.sda.dto.RoomDTO;
import org.com.sda.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PutMapping("/addRoom")
    public ResponseEntity addRoom(@RequestBody RoomDTO roomDTO){
        roomService.addRoom(roomDTO);
        return ResponseEntity.ok().build();
    }
}
