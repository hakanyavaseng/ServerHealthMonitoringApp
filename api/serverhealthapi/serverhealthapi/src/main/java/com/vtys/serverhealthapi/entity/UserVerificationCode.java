package com.vtys.serverhealthapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"verificationId"})
@ToString
@Entity
@Table(name = "UserVerificationCodes")
public class UserVerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer verificationId;

    @Column(name = "verificationCode", length = 10, nullable = false)
    private String verificationCode;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Users userid;

   
}