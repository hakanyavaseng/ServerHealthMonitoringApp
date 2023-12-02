package com.vtys.ssiuapi.service.impl;

import com.vtys.ssiuapi.dto.kullanicilarDTO;
import com.vtys.ssiuapi.models.kullanicilar;
import com.vtys.ssiuapi.repo.KullanicilarRepository;
import com.vtys.ssiuapi.repo.SunucularRepository;
import com.vtys.ssiuapi.service.KullanicilarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KullanicilarServiceImpl implements KullanicilarService {

    private final KullanicilarRepository kullanicilarRepository;
    private final SunucularRepository sunucularRepository;
    @Override
    @Transactional
    public kullanicilarDTO save(kullanicilarDTO kDTO) {

        kullanicilar k = new kullanicilar();
        k.setKullaniciadi(kDTO.getKullaniciadi());
        k.setKullanicisifre(kDTO.getKullanicisifre());
        k.setKullaniciemail(kDTO.getKullaniciemail());
        k.setKayittarihi(kDTO.getKayittarihi());
        k.setSongiris(kDTO.getSongiris());

        final kullanicilar kDb = kullanicilarRepository.save(k);
        kDTO.setKullaniciid(kDb.getKullaniciid());
        return  kDTO;

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<kullanicilarDTO> getAll() {
       List<kullanicilar> kullanicilarList = kullanicilarRepository.findAll();
       List<kullanicilarDTO> kullanicilarDTOList = new ArrayList<>();
       kullanicilarList.forEach(it -> {
           kullanicilarDTO kullanicilarDTO = new kullanicilarDTO();
              kullanicilarDTO.setKullaniciid(it.getKullaniciid());
           kullanicilarDTO.setKullaniciadi(it.getKullaniciadi());
           kullanicilarDTO.setKullanicisifre(it.getKullanicisifre());
           kullanicilarDTO.setKullaniciemail(it.getKullaniciemail());
           kullanicilarDTO.setKayittarihi(it.getKayittarihi());
           kullanicilarDTO.setSongiris(it.getSongiris());
           kullanicilarDTOList.add(kullanicilarDTO);
       });
        return kullanicilarDTOList;
    }

    @Override
    public Page<kullanicilar> getAll(Pageable pageable) {
        return null;
    }
}
