package com.vtys.serverhealthapi.controller;

import com.vtys.serverhealthapi.dto.ServerDto;
import com.vtys.serverhealthapi.entity.Servers;
import com.vtys.serverhealthapi.service.ServersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/servers")
public class ServersController {

    private final ServersService serversService;

    public ServersController(ServersService serversService) {
        this.serversService = serversService;
    }

    @GetMapping("/getall")
    public List<Servers> getAll() {
        return serversService.getAllServers();
    }

    @PostMapping("/create")
    public ResponseEntity<ServerDto> createHospital(@RequestBody ServerDto serverDto) {
        return ResponseEntity.ok(serversService.createServer(serverDto));
    }

    /*
     * @GetMapping("/getbycityname/{cityname}")
     * public List<Servers> getByCityName(@RequestParam String cityName) {
     * return serversService.findByCitynameNativeQuery(cityName);
     * }
     * 
     * @GetMapping("/getbyservercontaining/{servername}")
     * public List<Servers> getByServerName(@RequestParam String servername) {
     * return serversService.findByServernameContainingIgnoreCase(servername);
     * }
     */

}
