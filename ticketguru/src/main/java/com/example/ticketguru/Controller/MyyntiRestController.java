package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;

@RestController
@RequestMapping("/api/myynnit")
public class MyyntiRestController {

    private final MyyntiRepository myyntiRepository;

    public MyyntiRestController(MyyntiRepository myyntiRepository) {
        this.myyntiRepository = myyntiRepository;
    }

    @GetMapping
    public List<Myynti> getAllMyynnit() {
        return myyntiRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Myynti> getMyyntiById(@PathVariable Long id) {
        return myyntiRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Myynti> createMyynti(@RequestBody Myynti uusi) {
        Myynti tallennettu = myyntiRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Myynti> updateMyynti(@PathVariable Long id, @RequestBody Myynti updated) {
        return myyntiRepository.findById(id)
                .map(myynti -> {
                    myynti.setKayttaja(updated.getKayttaja());
                    myynti.setPaivamaara(updated.getPaivamaara());
                    myynti.setMaksutapa(updated.getMaksutapa());
                    myynti.setTyyppi(updated.getTyyppi());
                    myynti.setMyyntirivit(updated.getMyyntirivit());

                    Myynti saved = myyntiRepository.save(myynti);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMyynti(@PathVariable Long id) {
        return myyntiRepository.findById(id)
                .map(myynti -> {
                    myyntiRepository.delete(myynti);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}