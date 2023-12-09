package com.vtys.serverhealthapi.dto;

import com.vtys.serverhealthapi.entity.Locations;
import lombok.Data;

@Data
public class HospitalDto {
    private Integer hospitalid;
    private String hospitalname;
    private Locations cityid;

}
