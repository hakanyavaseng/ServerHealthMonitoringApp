package com.vtys.ssiuapi.dto;

import com.vtys.ssiuapi.models.sunucular;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class kullanicilarDTO {

    private Integer kullaniciid;
    private String kullaniciadi;
    private String kullanicisifre;
    private String kullaniciemail;
    private String kayittarihi;
    private String songiris;


}
