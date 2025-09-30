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

import com.example.ticketguru.model.LippuTyyppi;
import com.example.ticketguru.model.LippuTyyppiRepository;


@RestController
@RequestMapping("api/lipputyypit")
public class LipputyyppiRestController {

    private final LippuTyyppiRepository lippuTyyppiRepository;

    public LipputyyppiRestController(LippuTyyppiRepository lippuTyyppiRepository) {
        this.lippuTyyppiRepository = lippuTyyppiRepository;
    }

    @GetMapping
    public List<LippuTyyppi> getAllLiput() {
        return (List<LippuTyyppi>) lippuTyyppiRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<LippuTyyppi> createLippu(@RequestBody LippuTyyppi uusi) {
        LippuTyyppi tallennettu = lippuTyyppiRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LippuTyyppi> updateLippuTyyppi(@PathVariable Long id, @RequestBody LippuTyyppi updated) {
        return lippuTyyppiRepository.findById(id)
                .map(lippuTyyppi -> {
                    lippuTyyppi.setNimi(updated.getNimi());
                    lippuTyyppi.setHinta(updated.getHinta());

                    LippuTyyppi saved = lippuTyyppiRepository.save(lippuTyyppi);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLippuTyyppi(@PathVariable Long id) {
        return lippuTyyppiRepository.findById(id)
                .map(lippuTyyppi -> {
                    lippuTyyppiRepository.delete(lippuTyyppi);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
