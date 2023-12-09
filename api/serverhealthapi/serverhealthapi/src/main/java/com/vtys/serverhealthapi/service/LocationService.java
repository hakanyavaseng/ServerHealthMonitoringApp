package com.vtys.serverhealthapi.service;

import com.vtys.serverhealthapi.dto.LocationDto;

import java.util.List;

public interface LocationService {

    LocationDto getLocationById(Integer id);

    List<LocationDto> getAllLocations();



}
