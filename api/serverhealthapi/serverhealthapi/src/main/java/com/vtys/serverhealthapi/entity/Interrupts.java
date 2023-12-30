package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Interrupts")  // Specify the table name if it's different from the entity name
public class Interrupts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY strategy for MSSQL
    private Integer interruptid;

    @Column(name = "interruptdate", length = 30, nullable = false)
    private String interruptdate;

    @Column(name = "interrupttime", length = 20, nullable = false)
    private String interrupttime;

    @Column(name = "interruptstatus")
    private Boolean interruptstatus;

    @ManyToOne
    @JoinColumn(name = "serverid")
    private Servers serverid;
}