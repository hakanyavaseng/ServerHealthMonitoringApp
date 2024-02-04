package com.vtys.serverhealthapi.service;

import java.util.List;

import com.vtys.serverhealthapi.dto.InterruptsReportDailyDto;
import com.vtys.serverhealthapi.dto.InterruptsReportMonthlyDto;
import com.vtys.serverhealthapi.entity.Interrupts;

public interface InterruptsService {

    List<Interrupts> getAllInterrupts();

    List<Interrupts> findByServerNameNativeQuery(String servername);

    List<Interrupts> getInterruptsByServerid(Integer serverid);

    List<Interrupts> getInterruptsByHospitalname(String hospitalname);

    List<Interrupts> getInterruptsInOneWeek(Integer serverid);

    List<Interrupts> getInterruptsInOneMonth(Integer serverid);

    List<Interrupts> getInterruptsInOneYear(Integer serverid);

    List<InterruptsReportMonthlyDto> getInterruptsReportMonthly(Integer serverid);

    List<InterruptsReportDailyDto> getInterruptsReportDaily(Integer serverid);

}