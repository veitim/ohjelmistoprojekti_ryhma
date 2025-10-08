package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.Rooli;
import com.example.ticketguru.model.RooliRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/kayttajat")
public class KayttajaRestController {

    private final KayttajaRepository kayttajaRepository;
    private final RooliRepository rooliRepository;

    public KayttajaRestController(
        KayttajaRepository kayttajaRepository,
        RooliRepository rooliRepository
        ) 
        {
            this.kayttajaRepository = kayttajaRepository;
            this.rooliRepository = rooliRepository;
        }


    @GetMapping
    public List<Kayttaja> getAllKayttajat() {
        return (List<Kayttaja>) kayttajaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Kayttaja> createKayttaja(@Valid @RequestBody Kayttaja kayttaja) {
    Long rooliId = kayttaja.getRooli().getRooli_id();
    Rooli rooli = rooliRepository.findById(rooliId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Roolia id=" + rooliId + " ei löytynyt"));
    kayttaja.setRooli(rooli);
    Kayttaja tallennettu = kayttajaRepository.save(kayttaja);

    return ResponseEntity.ok(tallennettu);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Kayttaja> updateKayttaja(@Valid @PathVariable Long id, @RequestBody Kayttaja updated) {
    return kayttajaRepository.findById(id)
            .map(kayttaja -> {
                if (updated.getRooli() == null || updated.getRooli().getRooli_id() == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Rooli puuttuu päivityspyynnöstä");
                }

                Long rooliId = updated.getRooli().getRooli_id();
                Rooli rooli = rooliRepository.findById(rooliId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Roolia id=" + rooliId + " ei löytynyt"));
                kayttaja.setEtunimi(updated.getEtunimi());
                kayttaja.setSukunimi(updated.getSukunimi());
                kayttaja.setKatuosoite(updated.getKatuosoite());
                kayttaja.setSyntymaaika(updated.getSyntymaaika());
                kayttaja.setSahkoposti(updated.getSahkoposti());
                kayttaja.setPuhelinnro(updated.getPuhelinnro());
                kayttaja.setLisatieto(updated.getLisatieto());
                kayttaja.setPostinumero(updated.getPostinumero());
                kayttaja.setRooli(rooli);
                Kayttaja saved = kayttajaRepository.save(kayttaja);
                return ResponseEntity.ok(saved);
            })
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Käyttäjää id=" + id + " ei löytynyt"));
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