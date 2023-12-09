package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
