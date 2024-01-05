package com.vtys.serverhealthapi.service;

import java.util.List;

import com.vtys.serverhealthapi.entity.Healthdata;

public interface HealthdataService {

    List<Healthdata> findByServerid(Integer serverId);

    
}
