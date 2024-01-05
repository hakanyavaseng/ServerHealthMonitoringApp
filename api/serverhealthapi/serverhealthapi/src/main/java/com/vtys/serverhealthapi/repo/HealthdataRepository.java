package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Healthdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HealthdataRepository extends JpaRepository<Healthdata, Integer> {


    @Query(value = "EXEC sp_GetHealthdataByServerid :serverid", nativeQuery = true)
    List<Healthdata> findByServerid(Integer serverid);
}
