package com.vtys.serverhealthapi.service.impl;

import com.vtys.serverhealthapi.dto.ServerDto;
import com.vtys.serverhealthapi.entity.Hospitals;
import com.vtys.serverhealthapi.entity.Servers;
import com.vtys.serverhealthapi.repo.HospitalsRepository;
import com.vtys.serverhealthapi.repo.ServersRepository;
import com.vtys.serverhealthapi.service.EmailSenderService;
import com.vtys.serverhealthapi.service.ServersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ServersServiceImpl implements ServersService {

    private final ServersRepository serversRepository;
    private  final HospitalsRepository hospitalsRepository;
    private final ModelMapper modelMapper;

    private final EmailSenderService emailSenderService; // Inject EmailSenderService here


    @Override
    public List<Servers> getAllServers() {
        return serversRepository.findAll();
    }
    @Override
    public List<Servers>  findByServernameContainingIgnoreCase(String servername) {
        return serversRepository.findByServernameContainingIgnoreCase(servername);
    }

    @Override
    public ServerDto createServer(ServerDto serverDto) {
        Servers server = modelMapper.map(serverDto, Servers.class);

        // Set other properties
        server.setServername(serverDto.getServername());
        server.setServerip(serverDto.getServerip());
        server.setServeros(serverDto.getServeros());
        server.setServer_ram(serverDto.getServer_ram());
        server.setServerStorageType(serverDto.getServerStorageType());
        server.setServerStorageCapacity(serverDto.getServerStorageCapacity());

        // If hospitalid is of type Integer in ServerDto
        if (serverDto.getHospitalid() != null) {
            Hospitals hospital = hospitalsRepository.findById(serverDto.getHospitalid()).orElse(null);

            if (hospital != null) {
                server.setHostpitalid(hospital);
            } else {
                // Handle the case when the hospital with the given ID is not found
                // You may throw an exception, log a message, or handle it according to your application's logic
            }
        }

        // Save the Servers entity with the explicitly set ID
        serversRepository.saveAndFlush(server);

        // Set the explicitly set serverid in the DTO
        serverDto.setServerid(server.getServerid());

        return serverDto;
    }
    @Override
    public List<Servers> findByCitynameNativeQuery(String cityName) {
        
       // emailSenderService.sendEmail("hakanyavaseng@gmail.com", "Sehire gore server aramasi yapildi!", "API Islem Bilgisi");
       return serversRepository.findByCitynameNativeQuery(cityName);
    }



}
