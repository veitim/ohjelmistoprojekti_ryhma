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

import com.example.ticketguru.model.Tapahtuma;
import com.example.ticketguru.model.TapahtumaRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/tapahtumat")
public class TapahtumaRestController {

    private final TapahtumaRepository tapahtumaRepository;

    public TapahtumaRestController(TapahtumaRepository tapahtumaRepository) {
        this.tapahtumaRepository = tapahtumaRepository;
    }

    @GetMapping
    public List<Tapahtuma> getAllTapahtumat() {
        return (List<Tapahtuma>) tapahtumaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Tapahtuma> createTapahtuma(@Valid @RequestBody Tapahtuma uusi) {
        Tapahtuma tallennettu = tapahtumaRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tapahtuma> updateTapahtuma(@Valid @PathVariable Long id, @RequestBody Tapahtuma updated) {
        return tapahtumaRepository.findById(id)
                .map(tapahtuma -> {
                    tapahtuma.setNimi(updated.getNimi());
                    tapahtuma.setKatuosoite(updated.getKatuosoite());
                    tapahtuma.setJarjestaja(updated.getJarjestaja());
                    tapahtuma.setAlkamisPvm(updated.getAlkamisPvm());
                    tapahtuma.setPaattymisPvm(updated.getPaattymisPvm());
                    tapahtuma.setLisatiedot(updated.getLisatiedot());

                    Tapahtuma saved = tapahtumaRepository.save(tapahtuma);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTapahtuma(@PathVariable Long id) {
        return tapahtumaRepository.findById(id)
                .map(tapahtuma -> {
                    tapahtumaRepository.delete(tapahtuma);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
