package com.vtys.serverhealthapi.dto;

import lombok.Data;

@Data
public class InterruptsReportMonthlyDto {
    private String month;
    private int interruptCount;

    public InterruptsReportMonthlyDto(String month, int interruptCount) {
        this.month = month;
        this.interruptCount = interruptCount;
    }
}
