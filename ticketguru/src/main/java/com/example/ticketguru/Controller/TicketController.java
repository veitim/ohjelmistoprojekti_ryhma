package com.example.ticketguru.Controller;

import java.util.List;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketguru.model.Tapahtuma;
import com.example.ticketguru.model.TapahtumaRepository;

@RestController
@RequestMapping("/api/tapahtumat")
public class TicketController {

    private final TapahtumaRepository tapahtumaRepository;

    public TicketController(TapahtumaRepository tapahtumaRepository) {
        this.tapahtumaRepository = tapahtumaRepository;
    }

    @GetMapping
    public List<Tapahtuma> getAllTapahtumat() {
        return (List<Tapahtuma>) tapahtumaRepository.findAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Tapahtuma> updateTapahtuma(@PathVariable Long id, @RequestBody Tapahtuma updated) {
        return tapahtumaRepository.findById(id)
                .map(tapahtuma -> {
                    tapahtuma.setNimi(updated.getNimi());
                    tapahtuma.setKatuosoite(updated.getKatuosoite());
                    tapahtuma.setJarjestajaId(updated.getJarjestajaId());
                    tapahtuma.setAlkamisPvm(updated.getAlkamisPvm());
                    tapahtuma.setPaattymisPvm(updated.getPaattymisPvm());
                    tapahtuma.setHinta(updated.getHinta());
                    tapahtuma.setLisatiedot(updated.getLisatiedot());

                    Tapahtuma saved = tapahtumaRepository.save(tapahtuma);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
