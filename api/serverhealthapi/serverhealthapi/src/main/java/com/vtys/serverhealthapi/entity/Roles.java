package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "roleid" })
@ToString
@Entity
@Table(name = "Roles") // Specify the table name if it's different from the entity name
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY strategy for MSSQL
    private Integer roleid;

    private String rolename;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "roleid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    private List<Users> users;
}
