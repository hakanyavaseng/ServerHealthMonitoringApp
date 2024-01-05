package com.vtys.serverhealthapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtys.serverhealthapi.entity.Healthdata;
import com.vtys.serverhealthapi.service.HealthdataService;

@RestController
@RequestMapping("/api/healthdata")
public class HealthdataController {

    private final HealthdataService healthdataService;

    public HealthdataController(HealthdataService healthdataService) {
        this.healthdataService = healthdataService;
    }

    @GetMapping("getbyserverid/{serverid}")
    public List<Healthdata> getHealthdataByServerid(@PathVariable Integer serverid) {
        return healthdataService.findByServerid(serverid);
    }
    
}
