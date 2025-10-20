package com.example.ticketguru.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketguru.model.Jarjestaja;
import com.example.ticketguru.model.JarjestajaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jarjestajat/ADMIN")
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

    @PostMapping("/ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public Jarjestaja create(@Valid @RequestBody Jarjestaja jarjestaja) {
        return repository.save(jarjestaja);
    }

    @PutMapping("/ADMIN/{id}")
    public ResponseEntity<Jarjestaja> update(@PathVariable Long id, @Valid @RequestBody Jarjestaja updated) {
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

    @DeleteMapping("/ADMIN/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}


