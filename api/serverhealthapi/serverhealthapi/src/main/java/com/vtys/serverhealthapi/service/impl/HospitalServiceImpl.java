package com.vtys.serverhealthapi.service.impl;

import com.vtys.serverhealthapi.dto.HospitalDto;
import com.vtys.serverhealthapi.entity.Hospitals;
import com.vtys.serverhealthapi.repo.HospitalsRepository;
import com.vtys.serverhealthapi.repo.LocationsRepository;
import com.vtys.serverhealthapi.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final ModelMapper modelMapper;
    private final HospitalsRepository hospitalsRepository;
    private final LocationsRepository locationsRepository;


    //Create a hospital and save it to the database
    @Override
    public HospitalDto createHospital(HospitalDto hospitalDto) {
        Hospitals hospital = modelMapper.map(hospitalDto, Hospitals.class);


        hospital.setHospitalname(hospitalDto.getHospitalname());
        // cityid değerini set etme
        if (hospitalDto.getCityid() != null) {
            hospital.setCityid(hospitalDto.getCityid());
        }
        hospitalsRepository.save(hospital);
        hospitalDto.setHospitalid(hospital.getHospitalid());

        return hospitalDto;
    }

    //Get all hospitals from the database
    @Override
    public List<HospitalDto> getAllHospitals() {

        List<Hospitals> hospitals = hospitalsRepository.findAll();
        List<HospitalDto> hospitalDtos = new ArrayList<>();

        for (Hospitals hospital : hospitals) {
            hospitalDtos.add(modelMapper.map(hospital, HospitalDto.class));
        }
        return hospitalDtos;

    }


    //Get a hospital by id from the database
    @Override
    public HospitalDto getHospitalById(Integer id) {

        Hospitals hospital = hospitalsRepository.findById(id).orElse(null);
        if (hospital == null) {
            return null;
        }
        return modelMapper.map(hospital, HospitalDto.class);
    }

    //Get a hospital by city id from the database
    @Override
    public List<HospitalDto> getHospitalByCityId(Integer cityId) {
        List<Hospitals> hospitals = hospitalsRepository.findByCityid_Cityid(cityId);
        List<HospitalDto> hospitalDtos = new ArrayList<>();

        for (Hospitals hospital : hospitals) {
            hospitalDtos.add(modelMapper.map(hospital, HospitalDto.class));
        }

        return hospitalDtos;
    }

    @Override
    public List<Hospitals> findByHospitalnameContainingIgnoreCase(String hospitalName) {
        return hospitalsRepository.findByHospitalnameContainingIgnoreCase(hospitalName);
    }




}
