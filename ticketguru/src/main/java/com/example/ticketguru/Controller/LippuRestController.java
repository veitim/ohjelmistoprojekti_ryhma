package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/liput")
public class LippuRestController {

    private final LippuRepository lippuRepository;

    public LippuRestController(LippuRepository lippuRepository) {
        this.lippuRepository = lippuRepository;
    }


    @GetMapping
    public List<Lippu> getAllLiput() {
        return (List<Lippu>) lippuRepository.findAll();
    }

    @PostMapping("/ADMIN")
    public ResponseEntity<Lippu> createLippu(@Valid @RequestBody Lippu uusi) {
        Lippu tallennettu = lippuRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lippu> updateLippu(@Valid @PathVariable Long id, @RequestBody Lippu updated) {
        return lippuRepository.findById(id)
                .map(lippu -> {
                    lippu.setPaikka(updated.getPaikka());
                    lippu.setTila(updated.isTila());
                    lippu.setTapahtuma(updated.getTapahtuma());
                    lippu.setLipputyyppi(updated.getLipputyyppi());
                    lippu.setMyyntirivit(updated.getMyyntirivit());

                    Lippu saved = lippuRepository.save(lippu);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLippu(@PathVariable Long id) {
        return lippuRepository.findById(id)
                .map(lippu -> {
                    lippuRepository.delete(lippu);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

