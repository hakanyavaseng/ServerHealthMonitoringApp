package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locations_seq")
    @SequenceGenerator(name = "locations_seq", sequenceName = "locations_seq", allocationSize = 1)
    @Column(name = "cityid")
    private Integer cityid;


    @Column(name = "cityname", length = 20, nullable = false, unique = true)
    private String cityname;



}
