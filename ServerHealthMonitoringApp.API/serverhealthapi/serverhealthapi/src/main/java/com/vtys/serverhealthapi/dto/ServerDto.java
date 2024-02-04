package com.vtys.serverhealthapi.dto;

import lombok.Data;

@Data
public class ServerDto {
        private Integer serverid;
        private String servername;
        private String serverip;
        private String serveros;
        private String server_ram;
        private String serverStorageType;
        private String serverStorageCapacity;
        private Integer hospitalid; // Only include the hospital ID
}