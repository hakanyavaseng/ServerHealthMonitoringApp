package com.vtys.serverhealthapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtys.serverhealthapi.entity.Interrupts;
import com.vtys.serverhealthapi.service.InterruptsService;

@RestController
@RequestMapping("/interrupts")
public class InterruptsController {


    private final InterruptsService interruptsService;
    

    public InterruptsController(InterruptsService interruptsService) {
        this.interruptsService = interruptsService;
    }


    @GetMapping("/getall")
    public List<Interrupts> getAllInterrupts() {
        return interruptsService.getAllInterrupts();
    }

    @GetMapping("/getbyserverid/{serverid}")
    public List<Interrupts> getInterruptsByServerid(@PathVariable Integer serverid) {
        return interruptsService.getInterruptsByServerid(serverid);
    }

    @GetMapping("/getbyservername")
    public List<Interrupts> findByServerNameNativeQuery(@RequestParam String servername) {
        return interruptsService.findByServerNameNativeQuery(servername);
    }

    @GetMapping("/getbyhospitalname")
    public List<Interrupts> getInterruptsByHospitalname(@RequestParam String hospitalname) {
        return interruptsService.getInterruptsByHospitalname(hospitalname);
    }


    
}
