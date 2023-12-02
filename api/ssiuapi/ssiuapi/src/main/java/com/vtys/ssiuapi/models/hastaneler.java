package com.vtys.ssiuapi.models;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class hastaneler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hastaneid")
    private Integer hastaneid;

    @Column(name = "hastaneadi", length = 50, nullable = false, unique = true)
    private String hastaneadi;

    @ManyToOne
    @JoinColumn(name = "sehirid")
    private lokasyon sehirid;

    @OneToMany
    @JoinColumn(name = "sunucuid")
    private List<sunucular> sunucularList;

}
