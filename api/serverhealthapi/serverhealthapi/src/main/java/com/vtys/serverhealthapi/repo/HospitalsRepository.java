package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Hospitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalsRepository extends JpaRepository<Hospitals, Integer> {

    List<Hospitals> findByCityid_Cityid(Integer cityId);
}

