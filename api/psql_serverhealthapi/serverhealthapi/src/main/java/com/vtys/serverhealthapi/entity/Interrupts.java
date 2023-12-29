package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Interrupts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interrupts_seq")
    @SequenceGenerator(name = "interrupts_seq", sequenceName = "interrupts_seq", allocationSize = 1)
    private Integer interruptid;

    @Column(name = "interruptdate", length = 30, nullable = false, unique = true)
    private String interruptdate;

    @Column(name = "interrupttime", length = 20, nullable = false, unique = true)
    private String interrupttime;

    @Column(name = "interruptstatus")
    private Boolean interruptstatus;

    @ManyToOne
    @JoinColumn(name = "serverid")
    private Servers serverid;


}
