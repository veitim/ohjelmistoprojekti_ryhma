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
import com.example.ticketguru.model.Postinumero;
import com.example.ticketguru.model.PostinumeroRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/kayttajat/ADMIN")
public class KayttajaRestController {

    private final KayttajaRepository kayttajaRepository;
    private final PostinumeroRepository postinumeroRepository;

    public KayttajaRestController(
        KayttajaRepository kayttajaRepository,
        PostinumeroRepository postinumeroRepository
        ) 
        {
            this.kayttajaRepository = kayttajaRepository;
            this.postinumeroRepository = postinumeroRepository;
        }


    @GetMapping
    public List<Kayttaja> getAllKayttajat() {
        return (List<Kayttaja>) kayttajaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Kayttaja> createKayttaja(@Valid @RequestBody Kayttaja kayttaja) {
    String postinumeroId = kayttaja.getPostinumero().getPostinumero();
    Postinumero postinumero = postinumeroRepository.findByPostinumero(postinumeroId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Postinumeroa=" + postinumeroId + " ei löytynyt"));
    Kayttaja tallennettu = kayttajaRepository.save(kayttaja);
    kayttaja.setPostinumero(postinumero);

    return ResponseEntity.ok(tallennettu);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Kayttaja> updateKayttaja(@Valid @PathVariable Long id, @RequestBody Kayttaja updated) {
    return kayttajaRepository.findById(id)
            .map(kayttaja -> {

                if (updated.getPostinumero() == null || updated.getPostinumero().getPostinumero() == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Postinumero tarvitaan");
                }

                String postinumeroId = updated.getPostinumero().getPostinumero();
                Postinumero postinumero = postinumeroRepository.findByPostinumero(postinumeroId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "postinumeroa" + postinumeroId + " ei löytynyt"));

                kayttaja.setEtunimi(updated.getEtunimi());
                kayttaja.setSukunimi(updated.getSukunimi());
                kayttaja.setKatuosoite(updated.getKatuosoite());
                kayttaja.setSyntymaaika(updated.getSyntymaaika());
                kayttaja.setSahkoposti(updated.getSahkoposti());
                kayttaja.setPuhelinnro(updated.getPuhelinnro());
                kayttaja.setLisatieto(updated.getLisatieto());
                kayttaja.setPostinumero(postinumero);

                Kayttaja saved = kayttajaRepository.save(kayttaja);
                return ResponseEntity.ok(saved);
            })
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tietoja ei löydy"));
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