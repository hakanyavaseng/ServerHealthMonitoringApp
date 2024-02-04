package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Servers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServersRepository extends JpaRepository<Servers, Integer> {

    List<Servers> findByServernameContainingIgnoreCase(String serverName);

    @Query(value = "EXEC sp_GetServerByCity :cityName", nativeQuery = true)
    List<Servers> findByCitynameNativeQuery(String cityName);
}
