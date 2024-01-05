package com.vtys.serverhealthapi.dto;

import lombok.Data;

@Data
public class HealthdataDto {

        private Integer dataid;
        private String datadatetime;
        private Integer datacpuusage;
        private Integer dataramusage;
        private Integer datastorageusage;
        private Integer dataservertemp;
        private Integer dataambienttemp;
        private Integer dataenergyusage;
        private boolean dataheartbeat;
        private Integer serverid; 
    
}
