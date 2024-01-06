package com.vtys.serverhealthapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Servers")  // Specify the table name if it's different from the entity name
public class Servers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use IDENTITY strategy for MSSQL
    private Integer serverid;

    @Column(name = "servername", length = 50, nullable = false)
    private String servername;

    @Column(name = "serverip", length = 30, nullable = false, unique = true)
    private String serverip;

    private String serveros;
    private String server_ram;
    private String serverStorageType;
    private String serverStorageCapacity;

    // TODO JSON IGNORE YAPILDI, HATA VEREBİLİR İLERİDE


    @ManyToOne
    @JoinColumn(name = "hostpitalid")
    private Hospitals hostpitalid;

    @JsonIgnore
    @ManyToMany(mappedBy = "serversList")
    private List<Users> usersList;
}