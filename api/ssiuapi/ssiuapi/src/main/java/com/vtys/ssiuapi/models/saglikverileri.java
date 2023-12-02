package com.vtys.ssiuapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class saglikverileri {

    @Id
    @SequenceGenerator(name = "seq_veriid", allocationSize = 1)
    @GeneratedValue(generator = "seq_veriid", strategy = GenerationType.SEQUENCE)
    private Integer veriid;

    @Column(name = "veritarihsaat", length = 30, nullable = false, unique = true)
    private String veritarihsaat;

    @Column(name = "vericpukullanimi")
    private Integer vericpukullanimi;

    @Column(name = "veriramkullanimi")
    private Integer veriramkullanimi;

    @Column(name = "veridepolamakullanimi")
    private Integer veridepolamakullanimi;

    @Column(name = "verisunucusicakligi")
    private Integer verisunucusicakligi;

    @Column(name = "veriortamsicakligi")
    private Integer veriortamsicakligi;

    @Column(name = "veriguctuketimi")
    private Integer veriguctuketimi;

    @Column(name = "veriguctukullanimi")
    private boolean veriheartbeat;

    @ManyToOne
    @JoinColumn(name = "sunucuid")
    private sunucular sunucuid;


}
