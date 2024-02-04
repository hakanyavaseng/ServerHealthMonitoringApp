package com.vtys.serverhealthapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.vtys.serverhealthapi.dto.InterruptsReportDailyDto;
import com.vtys.serverhealthapi.dto.InterruptsReportMonthlyDto;
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

    @Override
    public List<InterruptsReportMonthlyDto> getInterruptsReportMonthly(Integer serverId) {
        List<Object[]> result = interruptsRepository.getInterruptsReportMonthly(serverId);
        return mapToObject(result);
    }

    private List<InterruptsReportMonthlyDto> mapToObject(List<Object[]> result) {
        return result.stream()
                .map(this::mapRowToDto)
                .collect(Collectors.toList());
    }

    private InterruptsReportMonthlyDto mapRowToDto(Object[] row) {
        String month = row[0] != null ? String.valueOf(row[0]) : null;
        Integer interruptCount = row[1] != null ? (Integer) row[1] : null;
        return new InterruptsReportMonthlyDto(month, interruptCount != null ? interruptCount.intValue() : null);
    }

    @Override
    public List<InterruptsReportDailyDto> getInterruptsReportDaily(Integer serverid) {
        List<Object[]> result = interruptsRepository.getInterruptsReportDaily(serverid);
        return mapToObjectDaily(result);
    }

    private List<InterruptsReportDailyDto> mapToObjectDaily(List<Object[]> result) {
        return result.stream()
                .map(this::mapRowToDtoDaily)
                .collect(Collectors.toList());
    }

    private InterruptsReportDailyDto mapRowToDtoDaily(Object[] row) {
        String day = row[0] != null ? String.valueOf(row[0]) : null;
        Integer interruptCount = row[1] != null ? (Integer) row[1] : null;
        return new InterruptsReportDailyDto(day, interruptCount != null ? interruptCount.intValue() : null);
    }

}
