package com.vtys.serverhealthapi.service;

import com.vtys.serverhealthapi.dto.LocationDto;
import com.vtys.serverhealthapi.entity.Locations;

import java.util.List;

public interface LocationService {

    LocationDto getLocationById(Integer id);

    List<Locations> getAllLocations();

    Locations getByCityid(Integer locationid);





}
