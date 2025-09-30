package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ticketguru.model.Jarjestaja;
import com.example.ticketguru.model.JarjestajaRepository;

@RestController
@RequestMapping("/api/jarjestajat")
public class JarjestajaRestController {

    private final JarjestajaRepository repository;

    public JarjestajaRestController(JarjestajaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Jarjestaja> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
        public ResponseEntity<Jarjestaja> 
    getJarjestajaById(@PathVariable Long id) {
            return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jarjestaja create(@RequestBody Jarjestaja jarjestaja) {
        return repository.save(jarjestaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jarjestaja> update(@PathVariable Long id, @RequestBody Jarjestaja updated) {
        return repository.findById(id)
                        .map(jarjestaja -> {
                          jarjestaja.setNimi(updated.getNimi());
                          jarjestaja.setYhteyshenkilo(updated.getYhteyshenkilo());
                          jarjestaja.setSahkoposti(updated.getSahkoposti());
                          jarjestaja.setPuhelin(updated.getPuhelin());
                          return ResponseEntity.ok(repository.save(jarjestaja)); 
                        })
                        .orElse(ResponseEntity.notFound().build());
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


