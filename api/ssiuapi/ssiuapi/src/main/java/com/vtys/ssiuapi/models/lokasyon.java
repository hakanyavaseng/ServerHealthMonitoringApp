package com.vtys.ssiuapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class lokasyon {

    @Id
    @SequenceGenerator(name = "seq_sehirid", allocationSize = 1)
    @GeneratedValue(generator = "seq_sehirid", strategy = GenerationType.SEQUENCE)
    @Column(name = "sehirid")
    private Integer sehirid;


    @Column(name = "sehiradi", length = 20, nullable = false, unique = true)
    private String sehiradi;


    @OneToMany
    @JoinColumn(name = "sehirid")
    private List<hastaneler> hastanelerList;


}
