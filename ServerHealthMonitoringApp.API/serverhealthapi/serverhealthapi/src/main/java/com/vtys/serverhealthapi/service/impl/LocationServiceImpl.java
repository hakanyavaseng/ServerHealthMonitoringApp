package com.vtys.serverhealthapi.service.impl;

import com.vtys.serverhealthapi.dto.LocationDto;
import com.vtys.serverhealthapi.entity.Locations;
import com.vtys.serverhealthapi.repo.LocationsRepository;
import com.vtys.serverhealthapi.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final ModelMapper modelMapper;
    private final LocationsRepository locationRepository;

    @Override
    public LocationDto getLocationById(Integer id) {

        Locations location = locationRepository.findById(id).orElse(null);
        if (location == null) {
            return null;
        }
        return modelMapper.map(location, LocationDto.class);

    }

    @Override
    public List<Locations> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Locations getByCityid(Integer locationid) {
        return locationRepository.getByCityid(locationid);
    }
}
