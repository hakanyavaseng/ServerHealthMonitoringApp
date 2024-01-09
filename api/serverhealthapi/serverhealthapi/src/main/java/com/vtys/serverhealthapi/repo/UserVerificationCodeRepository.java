package com.vtys.serverhealthapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtys.serverhealthapi.entity.UserVerificationCode;

public interface UserVerificationCodeRepository extends JpaRepository<UserVerificationCode, Integer> {

    Optional<UserVerificationCode> findByUsername(String username);

}