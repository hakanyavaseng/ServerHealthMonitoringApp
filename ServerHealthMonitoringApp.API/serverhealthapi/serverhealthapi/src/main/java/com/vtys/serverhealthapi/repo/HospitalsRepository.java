package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Hospitals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalsRepository extends JpaRepository<Hospitals, Integer> {

    List<Hospitals> findByCityid_Cityid(Integer cityId);

    List<Hospitals> findByHospitalnameContainingIgnoreCase(String hospitalname);
}
