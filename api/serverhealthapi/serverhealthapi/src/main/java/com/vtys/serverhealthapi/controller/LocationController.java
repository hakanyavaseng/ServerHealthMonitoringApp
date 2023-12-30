package com.vtys.serverhealthapi.controller;

import com.vtys.serverhealthapi.entity.Locations;
import com.vtys.serverhealthapi.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationservice;

    public LocationController(LocationService locationservice) {
        this.locationservice = locationservice;
    }

    @GetMapping("/getall")
    public List<Locations> getAll() {
        return locationservice.getAllLocations();
    }


    @GetMapping("/getbyid/{id}")
    public Locations getById(@PathVariable Integer id) {
        return locationservice.getByCityid(id);
    }

}
