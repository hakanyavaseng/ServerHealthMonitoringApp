package com.vtys.serverhealthapi.service;

import java.util.List;

import com.vtys.serverhealthapi.entity.Healthdata;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface HealthdataService {

    List<Healthdata> findByServerid(Integer serverId);
    List<Healthdata> getHealthdataInOneWeek(Integer serverid);
    List<Healthdata> getHealthdataInOneMonth(Integer serverid);
    List<Healthdata> getHealthdataInOneYear(Integer serverid);


    
}
