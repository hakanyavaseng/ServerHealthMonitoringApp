package com.vtys.serverhealthapi.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vtys.serverhealthapi.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{
    
    Optional<Roles> findByRolename(String rolename);

}
