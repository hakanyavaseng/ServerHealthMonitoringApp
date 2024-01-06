package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Hospitals")  // Specify the table name if it's different from the entity name
public class Hospitals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY strategy for MSSQL
    @Column(name = "hospitalid")
    private Integer hospitalid;

    @Column(name = "hospitalname", length = 50, nullable = false)
    private String hospitalname;

    @ManyToOne
    @JoinColumn(name = "cityid")
    private Locations cityid;
}