package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {


    Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);

    




}
