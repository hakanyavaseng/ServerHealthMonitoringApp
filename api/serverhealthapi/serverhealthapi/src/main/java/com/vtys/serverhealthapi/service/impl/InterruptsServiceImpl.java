package com.vtys.serverhealthapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vtys.serverhealthapi.entity.Interrupts;
import com.vtys.serverhealthapi.repo.InterruptsRepository;
import com.vtys.serverhealthapi.service.InterruptsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterruptsServiceImpl implements InterruptsService {

    private final InterruptsRepository interruptsRepository;


    @Override
    public List<Interrupts> getAllInterrupts() {
       return interruptsRepository.findAll();
    }

    @Override
    public List<Interrupts> findByServerNameNativeQuery(String servername) {
        return interruptsRepository.findByServerNameNativeQuery(servername);

    }

    @Override
    public List<Interrupts> getInterruptsByServerid(Integer serverid) {
    return interruptsRepository.getInterruptsByServerid(serverid);
      
    }

    @Override
    public List<Interrupts> getInterruptsByHospitalname(String hospitalname) {
        return interruptsRepository.getInterruptsByHospitalname(hospitalname);
    }

    @Override
    public List<Interrupts> getInterruptsInOneWeek(Integer serverid) {
        return interruptsRepository.getInterruptsInOneWeek(serverid);
    }

    @Override
    public List<Interrupts> getInterruptsInOneMonth(Integer serverid) {
        return interruptsRepository.getInterruptsInOneMonth(serverid);
    }

    @Override
    public List<Interrupts> getInterruptsInOneYear(Integer serverid) {
        return interruptsRepository.getInterruptsInOneYear(serverid);
    }

}
