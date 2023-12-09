package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Locations, Integer> {
}
