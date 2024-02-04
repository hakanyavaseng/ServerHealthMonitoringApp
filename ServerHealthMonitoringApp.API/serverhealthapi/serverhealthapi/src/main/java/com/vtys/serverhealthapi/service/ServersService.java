package com.vtys.serverhealthapi.service;

import com.vtys.serverhealthapi.dto.ServerDto;
import com.vtys.serverhealthapi.entity.Servers;

import java.util.List;

public interface ServersService {

    List<Servers> getAllServers();

    List<Servers> findByServernameContainingIgnoreCase(String servername);

    ServerDto createServer(ServerDto serverDto);

    List<Servers> findByCitynameNativeQuery(String cityName);

}
