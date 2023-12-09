package com.vtys.serverhealthapi.dto;

import lombok.Data;

@Data
public class ServerDto {
        private Integer serverid;
        private String servername;
        private String serverip;
        private String serveros;
        private String serverram;
        private String serverstoragetype;
        private String serverstoragecapacity;
        private Integer hospitalid;
}
