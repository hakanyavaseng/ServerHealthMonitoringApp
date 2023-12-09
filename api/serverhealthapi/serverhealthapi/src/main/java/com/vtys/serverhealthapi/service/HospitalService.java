package com.vtys.serverhealthapi.service;

import com.vtys.serverhealthapi.dto.HospitalDto;

import java.util.List;

public interface HospitalService {

    HospitalDto createHospital(HospitalDto hospitalDto);

    List<HospitalDto> getAllHospitals();

    HospitalDto getHospitalById(Integer id);

    List<HospitalDto> getHospitalByCityId(Integer cityId);


}
