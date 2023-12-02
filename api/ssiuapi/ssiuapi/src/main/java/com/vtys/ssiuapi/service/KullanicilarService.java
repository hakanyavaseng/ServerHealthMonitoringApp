package com.vtys.ssiuapi.service;

import com.vtys.ssiuapi.dto.kullanicilarDTO;
import com.vtys.ssiuapi.models.kullanicilar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KullanicilarService {

    kullanicilarDTO save(kullanicilarDTO kisiDto);

    void delete(Integer id);

    List<kullanicilarDTO> getAll();

    Page<kullanicilar> getAll(Pageable pageable);

}
