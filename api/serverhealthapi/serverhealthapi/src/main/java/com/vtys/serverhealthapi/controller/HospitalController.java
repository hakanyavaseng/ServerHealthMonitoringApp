package com.vtys.serverhealthapi.controller;

import com.vtys.serverhealthapi.dto.HospitalDto;
import com.vtys.serverhealthapi.dto.LocationDto;
import com.vtys.serverhealthapi.entity.Hospitals;
import com.vtys.serverhealthapi.repo.HospitalsRepository;
import com.vtys.serverhealthapi.service.HospitalService;
import com.vtys.serverhealthapi.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;
    private  final HospitalsRepository hospitalsRepository;

    public HospitalController(HospitalService hospitalService, HospitalsRepository hospitalsRepository) {
        this.hospitalService = hospitalService;
        this.hospitalsRepository = hospitalsRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<HospitalDto> createHospital(@RequestBody HospitalDto hospitalDto) {
        return ResponseEntity.ok(hospitalService.createHospital(hospitalDto));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<HospitalDto>> getAll() {
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<HospitalDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @GetMapping("/getbycityid/{cityid}")
    public ResponseEntity<List<HospitalDto>> getByCityId(@PathVariable Integer cityid) {
        return ResponseEntity.ok(hospitalService.getHospitalByCityId(cityid));
    }

    @GetMapping("/getbyhospitalname")
    public List<Hospitals> getByHospitalName(@RequestParam String hospitalname) {
        return hospitalsRepository.findByHospitalnameContainingIgnoreCase(hospitalname);
    }



}
