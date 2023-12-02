package com.vtys.ssiuapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"kullaniciid"})
@ToString
@Entity
public class kullanicilar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer kullaniciid;

    @Column(name = "kullaniciadi", length = 20, nullable = false, unique = true)
    private String kullaniciadi;

    @Column(name = "kullanicisifre", length = 30, nullable = false)
    private String kullanicisifre;

    @Column(name = "kullaniciyetki", length = 10)
    private String kullaniciyetki;

    @Column(name = "kullaniciemail", length = 50, nullable = false, unique = true)
    private String kullaniciemail;

    @Column(name = "kayittarihi", length = 30, nullable = false)
    private String kayittarihi;

    @Column(name = "songiris", length = 30, nullable = false)
    private String songiris;

    @ManyToMany
    @JoinTable(
            name = "kullanicilar_sunucular",
            joinColumns = @JoinColumn(name = "kullaniciid"),
            inverseJoinColumns = @JoinColumn(name = "sunucuid")

    )
    private List<sunucular> sunucularList;


}
