package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Locations") // Specify the table name if it's different from the entity name
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY strategy for MSSQL
    @Column(name = "cityid")
    private Integer cityid;

    @Column(name = "cityname", length = 20, nullable = false, unique = true)
    private String cityname;
}