package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.KayttajaRepository;

@RestController
@RequestMapping("/api/kayttajat")
public class KayttajaRestController {

    private final KayttajaRepository kayttajaRepository;

    public KayttajaRestController(KayttajaRepository kayttajaRepository) {
        this.kayttajaRepository = kayttajaRepository;
    }


    @GetMapping
    public List<Kayttaja> getAllKayttajat() {
        return (List<Kayttaja>) kayttajaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Kayttaja> createKayttaja(@RequestBody Kayttaja uusi) {
        Kayttaja tallennettu = kayttajaRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kayttaja> updateKayttaja(@PathVariable Long id, @RequestBody Kayttaja updated) {
        return kayttajaRepository.findById(id)
                .map(kayttaja -> {
                    kayttaja.setEtunimi(updated.getEtunimi());
                    kayttaja.setSukunimi(updated.getSukunimi());
                    kayttaja.setKatuosoite(updated.getKatuosoite());
                    kayttaja.setSyntymaaika(updated.getSyntymaaika());
                    kayttaja.setSahkoposti(updated.getSahkoposti());
                    kayttaja.setPuhelinnro(updated.getPuhelinnro());
                    kayttaja.setLisatieto(updated.getLisatieto());
                    kayttaja.setPostinumero(updated.getPostinumero());
                    kayttaja.setRooli(updated.getRooli());

                    Kayttaja saved = kayttajaRepository.save(kayttaja);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKayttaja(@PathVariable Long id) {
        return kayttajaRepository.findById(id)
                .map(kayttaja -> {
                    kayttajaRepository.delete(kayttaja);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}