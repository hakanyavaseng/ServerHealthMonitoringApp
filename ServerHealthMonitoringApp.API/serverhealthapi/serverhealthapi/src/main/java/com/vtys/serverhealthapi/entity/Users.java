package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "userid" })
@ToString
@Entity
@Table(name = "Users") // Specify the table name if it's different from the entity name
public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY strategy for MSSQL
        private Integer userid;

        @Column(name = "username", length = 20, nullable = false, unique = true)
        private String username;

        @Column(name = "userpassword", length = 150, nullable = false)
        private String userpassword;

        @Column(name = "userauthority", length = 10)
        private String userauthority;

        @Column(name = "useremail", length = 50, nullable = false, unique = true)
        private String useremail;

        @Column(name = "userregistrationdate", length = 30, nullable = false)
        private String userregistrationdate;

        @Column(name = "userlastlogin", length = 30, nullable = false)
        private String userlastlogin;

        @ManyToMany
        @JoinTable(name = "users_servers", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "serverid"))
        private List<Servers> serversList;

        @ManyToMany
        @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
        private List<Roles> rolesList;
}