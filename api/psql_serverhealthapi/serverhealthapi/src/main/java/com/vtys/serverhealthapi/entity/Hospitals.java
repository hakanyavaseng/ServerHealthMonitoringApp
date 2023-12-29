package com.vtys.serverhealthapi.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Hospitals {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospitals_seq")
    @SequenceGenerator(name = "hospitals_seq", sequenceName = "hospitals_seq", allocationSize = 1)
    @Column(name = "hospitalid")
    private Integer hospitalid;

    @Column(name = "hospitalname", length = 50, nullable = false, unique = true)
    private String hospitalname;

    @ManyToOne
    @JoinColumn(name = "cityid")
    private Locations cityid;



}
