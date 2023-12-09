package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Healthdata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthdataRepository extends JpaRepository<Healthdata, Integer> {
}
