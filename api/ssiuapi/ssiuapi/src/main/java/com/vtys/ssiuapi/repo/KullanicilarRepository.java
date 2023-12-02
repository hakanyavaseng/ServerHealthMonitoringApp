package com.vtys.ssiuapi.repo;

import com.vtys.ssiuapi.models.kullanicilar;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface KullanicilarRepository extends JpaRepository<kullanicilar, Integer>{

}
