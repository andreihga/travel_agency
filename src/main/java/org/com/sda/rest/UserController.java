package org.com.sda.rest;

import org.com.sda.dto.UserDTO;
import org.com.sda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping(value = "/signUp", consumes = "application/json")
    public ResponseEntity signUp(@RequestBody UserDTO userDTO){
        userService.signUp(userDTO);
        return new ResponseEntity("SignUp Successfully", HttpStatus.OK);
    }
    @GetMapping(value = "/logIn", consumes = "application/json")
    public ResponseEntity logIn(@RequestBody UserDTO userDTO){
        return new ResponseEntity(userService.logIn(userDTO),HttpStatus.OK);
    }
}
