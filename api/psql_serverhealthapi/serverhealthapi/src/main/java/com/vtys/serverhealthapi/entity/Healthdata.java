package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Healthdata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "healthdata_seq")
    @SequenceGenerator(name = "healthdata_seq", sequenceName = "healthdata_seq", allocationSize = 1)
    private Integer dataid;

    @Column(name = "datadatetime", length = 30, nullable = false, unique = true)
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
