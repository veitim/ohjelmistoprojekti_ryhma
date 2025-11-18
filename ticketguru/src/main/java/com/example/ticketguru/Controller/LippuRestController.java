package com.example.ticketguru.Controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketguru.Service.QrService;
import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/liput")
public class LippuRestController {

    private final LippuRepository lippuRepository;
    private final QrService qrService;

    public LippuRestController(LippuRepository lippuRepository, QrService qrService) {
        this.lippuRepository = lippuRepository;
          this.qrService = qrService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lippu> getLippuById(@PathVariable Long id) {
        return lippuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
        public ResponseEntity<?> getLippuByKoodi(@RequestParam(required = false) String koodi) {
        if (koodi != null) {
            return lippuRepository.findByKoodi(koodi)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.ok(lippuRepository.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Lippu> createLippu(@Valid @RequestBody Lippu uusi) {
        Lippu tallennettu = lippuRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Lippu> updateLippu(@Valid @PathVariable Long id, @RequestBody Lippu updated) {
        return lippuRepository.findById(id)
                .map(lippu -> {
                    lippu.setPaikka(updated.getPaikka());
                    lippu.setTila(updated.isTila());
                    lippu.setKaytetty(updated.isKaytetty());
                    lippu.setLipputyyppi(updated.getLipputyyppi());

                    Lippu saved = lippuRepository.save(lippu);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLippu(@PathVariable Long id) {
        return lippuRepository.findById(id)
                .map(lippu -> {
                    lippuRepository.delete(lippu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Lippu> patchLippu(@Valid @PathVariable Long id, @RequestBody Lippu patched) {
        return lippuRepository.findById(id)
            .map(lippu -> {
                lippu.setKaytetty(patched.isKaytetty());

                Lippu saved = lippuRepository.save(lippu);
                return ResponseEntity.ok(saved);
            })
            .orElse(ResponseEntity.notFound().build());
    }
 @GetMapping("/{id}/qr")
public ResponseEntity<ByteArrayResource> getLippuQr(@PathVariable Long id) {
    var optionalLippu = lippuRepository.findById(id);

    if (optionalLippu.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    var lippu = optionalLippu.get();

    try {
        String qrContent = "https://app.example.com/redeem?lippuId=" + lippu.getLippu_id();

        byte[] qrBytes = qrService.generateQrPng(qrContent, 400);
        ByteArrayResource resource = new ByteArrayResource(qrBytes);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(qrBytes.length)
                .body(resource);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }
}
}
    


