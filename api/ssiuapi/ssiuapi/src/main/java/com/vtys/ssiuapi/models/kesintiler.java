package com.vtys.ssiuapi.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class kesintiler {

    @Id
    @SequenceGenerator(name = "seq_kesintiid", allocationSize = 1)
    @GeneratedValue(generator = "seq_kesintiid", strategy = GenerationType.SEQUENCE)
    private Integer kesintiid;

    @Column(name = "kesintitarih", length = 30, nullable = false, unique = true)
    private String kesintitarih;

    @Column(name = "kesintizaman", length = 20, nullable = false, unique = true)
    private String kesintizaman;

    @Column(name = "kesintidurum")
    private Boolean kesintidurum;

    @ManyToOne
    @JoinColumn(name = "sunucuid")
    private sunucular sunucuid;






}
