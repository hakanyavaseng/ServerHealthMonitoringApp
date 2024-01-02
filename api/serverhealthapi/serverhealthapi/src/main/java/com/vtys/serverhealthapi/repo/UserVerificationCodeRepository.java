package com.vtys.serverhealthapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vtys.serverhealthapi.entity.UserVerificationCode;
import com.vtys.serverhealthapi.entity.Users;

public interface UserVerificationCodeRepository extends JpaRepository<UserVerificationCode, Integer>{

    Optional<UserVerificationCode> findByUsername(String username);

    
}