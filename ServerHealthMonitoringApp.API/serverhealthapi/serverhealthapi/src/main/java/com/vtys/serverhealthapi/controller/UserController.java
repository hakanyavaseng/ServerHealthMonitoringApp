package com.vtys.serverhealthapi.controller;

import com.vtys.serverhealthapi.dto.UserDto;
import com.vtys.serverhealthapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    // Initialize UserService
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userservice.createUser(userDto));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userservice.getAllUsers());
    }

}
