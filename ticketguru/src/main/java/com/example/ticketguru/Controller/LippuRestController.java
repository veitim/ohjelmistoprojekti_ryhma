package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;

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

    @PostMapping
    public ResponseEntity<Lippu> createLippu(@RequestBody Lippu uusi) {
        Lippu tallennettu = lippuRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lippu> updateLippu(@PathVariable Long id, @RequestBody Lippu updated) {
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

