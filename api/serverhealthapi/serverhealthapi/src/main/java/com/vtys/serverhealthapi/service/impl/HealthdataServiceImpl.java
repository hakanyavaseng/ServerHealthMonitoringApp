package com.vtys.serverhealthapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vtys.serverhealthapi.entity.Healthdata;
import com.vtys.serverhealthapi.repo.HealthdataRepository;
import com.vtys.serverhealthapi.service.HealthdataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthdataServiceImpl implements HealthdataService {

    private final HealthdataRepository healthdataRepository;

   
    @Override
    public List<Healthdata> findByServerid(Integer serverId) {
        return healthdataRepository.findByServerid(serverId);

    }
    
}
