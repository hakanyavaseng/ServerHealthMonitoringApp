package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Healthdata") // Specify the table name if it's different from the entity name
public class Healthdata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY strategy for MSSQL
    private Integer dataid;

    @Column(name = "datadatetime", length = 30, nullable = false)
    private String datadatetime;

    @Column(name = "datacpuusage")
    private Integer datacpuusage;

    @Column(name = "dataramusage")
    private Integer dataramusage;

    @Column(name = "datastorageusage")
    private Integer datastorageusage;

    @Column(name = "dataservertemp")
    private Integer dataservertemp;

    @Column(name = "dataambienttemp")
    private Integer dataambienttemp;

    @Column(name = "dataenergyusage")
    private Integer dataenergyusage;

    @Column(name = "dataheartbeat")
    private boolean dataheartbeat;

    @ManyToOne
    @JoinColumn(name = "serverid")
    private Servers serverid;
}