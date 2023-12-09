package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Interrupts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterruptsRepository extends JpaRepository<Interrupts, Integer> {
}
