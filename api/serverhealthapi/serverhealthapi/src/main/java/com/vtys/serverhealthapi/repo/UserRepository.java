package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.dto.GoogleLoginDto;
import com.vtys.serverhealthapi.dto.UserDto;
import com.vtys.serverhealthapi.entity.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {


    Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByUseremail(String useremail);



}
