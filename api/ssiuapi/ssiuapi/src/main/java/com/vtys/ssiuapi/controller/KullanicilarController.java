package com.vtys.ssiuapi.controller;

import com.vtys.ssiuapi.dto.kullanicilarDTO;
import com.vtys.ssiuapi.service.KullanicilarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kullanici")
public class KullanicilarController {

   private final KullanicilarService  kService;

    public KullanicilarController(KullanicilarService kService) {
        this.kService = kService;
    }

    @PostMapping
    public ResponseEntity<kullanicilarDTO> kaydet(@RequestBody kullanicilarDTO kDTO) {
        return ResponseEntity.ok(kService.save(kDTO));
    }

    @GetMapping
    public ResponseEntity<List<kullanicilarDTO>> listele() {
        return ResponseEntity.ok(kService.getAll());
    }


}
