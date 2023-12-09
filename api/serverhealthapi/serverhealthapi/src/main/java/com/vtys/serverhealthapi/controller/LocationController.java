package com.vtys.serverhealthapi.controller;

import com.vtys.serverhealthapi.dto.LocationDto;
import com.vtys.serverhealthapi.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationservice;

    public LocationController(LocationService locationservice) {
        this.locationservice = locationservice;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<LocationDto>> getAll() {
        return ResponseEntity.ok(locationservice.getAllLocations());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<LocationDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(locationservice.getLocationById(id));
    }

}
