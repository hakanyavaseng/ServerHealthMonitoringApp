package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import java.awt.*;
import java.util.ListIterator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"roleid"})
@ToString
@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "roles_seq", allocationSize = 1)
    private Integer roleid;

    private String rolename;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "roleid"),
            inverseJoinColumns = @JoinColumn(name = "userid")
    )
    private List<Users> users;

}
