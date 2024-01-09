package com.vtys.serverhealthapi.dto;

import lombok.Data;

@Data
public class InterruptsReportDailyDto {
    private String day;
    private Integer interruptCount;

    public InterruptsReportDailyDto(String day, Integer interruptCount) {
        this.day = day;
        this.interruptCount = interruptCount;
    }

}
