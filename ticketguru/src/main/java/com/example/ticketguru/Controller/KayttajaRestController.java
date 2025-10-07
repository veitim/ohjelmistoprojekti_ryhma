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

import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.PostinumeroRepository;
import com.example.ticketguru.model.RooliRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/kayttajat")
public class KayttajaRestController {

    private final KayttajaRepository kayttajaRepository;
    private final PostinumeroRepository postinumeroRepository;
    private final RooliRepository rooliRepository;

    public KayttajaRestController(
        KayttajaRepository kayttajaRepository, 
        PostinumeroRepository postinumeroRepository, 
        RooliRepository rooliRepository
        ) 
        {
            this.kayttajaRepository = kayttajaRepository;
            this.postinumeroRepository = postinumeroRepository;
            this.rooliRepository = rooliRepository;
        }


    @GetMapping
    public List<Kayttaja> getAllKayttajat() {
        return (List<Kayttaja>) kayttajaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Kayttaja> createKayttaja(@Valid @RequestBody Kayttaja uusi) {
        Kayttaja tallennettu = kayttajaRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Kayttaja> updateKayttaja(@Valid @PathVariable Long id, @RequestBody Kayttaja updated) {
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