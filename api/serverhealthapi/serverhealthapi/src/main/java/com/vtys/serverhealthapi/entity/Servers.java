package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"serverid"})
@ToString
@Entity
public class Servers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servers_seq")
    @SequenceGenerator(name = "servers_seq", sequenceName = "servers_seq", allocationSize = 1)
    private Integer serverid;

    @Column(name = "servername", length = 50, nullable = false, unique = true)
    private String servername;

    @Column(name = "serverip", length = 30, nullable = false, unique = true)
    private String serverip;

    private String serveros;
    private String server_ram;
    private String serverStorageType;
    private String serverStorageCapacity;

    @OneToMany
    @JoinColumn(name = "cityid")
    private List<Healthdata> healthdataList;

    @OneToMany
    @JoinColumn(name = "interruptid")
    private List<Interrupts> interruptsList;

    @ManyToOne
    @JoinColumn(name = "hostpitalid")
    private Hospitals hastaneid;

    @ManyToMany(mappedBy = "serversList")
    private List<Users> usersList;


}
