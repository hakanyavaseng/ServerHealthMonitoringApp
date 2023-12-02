package com.vtys.ssiuapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"sunucuid"})
@ToString
@Entity
public class sunucular {

    @Id
    @SequenceGenerator(name = "seq_sunucuid", allocationSize = 1)
    @GeneratedValue(generator = "seq_sunucuid", strategy = GenerationType.SEQUENCE)
    private Integer sunucuid;

    @Column(name = "sunucuadi", length = 50, nullable = false, unique = true)
    private String sunucuadi;

    @Column(name = "sunucuip", length = 30, nullable = false, unique = true)
    private String sunucuip;

    private String sunucuos;
    private String sunucuram;
    private String sunucudepolamatipi;
    private String sunucudepolamakapasitesi;

    @OneToMany
    @JoinColumn(name = "sehirid")
    private List<saglikverileri> saglikverileriList;

    @OneToMany
    @JoinColumn(name = "kesintiid")
    private List<kesintiler> kesintilerList;

    @ManyToOne
    @JoinColumn(name = "hastaneid")
    private hastaneler hastaneid;

    @ManyToMany(mappedBy = "sunucularList")
    private List<kullanicilar> kullanicilarList;




}
