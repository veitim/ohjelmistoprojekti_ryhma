package com.example.ticketguru.Controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.ticketguru.Service.MyyntiService;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.Myyntirivi;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/myynnit")
public class MyyntiRestController {
    
    private final MyyntiRepository repository;
    private final MyyntiService myyntiService;

    public MyyntiRestController(MyyntiRepository repository, MyyntiService myyntiService) {
        this.repository = repository;
        this.myyntiService = myyntiService;
    }

    @GetMapping
    public Iterable<Myynti> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Myynti> getMyyntiById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> luoMyynti(@Valid @RequestBody Myynti myynti){
        try {
            Myynti tallennettu = myyntiService.luoMyynti(myynti);
            return ResponseEntity.ok(tallennettu);
        } catch (IllegalStateException virhe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(virhe.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Myynti updated) {
    return repository.findById(id)
            .map(existing -> {
                try {
                    existing.setKayttaja(updated.getKayttaja());
                    existing.setPaivamaara(updated.getPaivamaara());
                    existing.setMaksutapa(updated.getMaksutapa());
                    existing.setSumma(updated.getSumma());

                    existing.getMyyntirivit().clear();
                    for (Myyntirivi rivi : updated.getMyyntirivit()) {
                        rivi.setMyynti(existing);
                        existing.getMyyntirivit().add(rivi);
                    }
                    Myynti saved = myyntiService.luoMyynti(existing);
                    return ResponseEntity.ok(saved);
                } catch (org.springframework.dao.DataIntegrityViolationException e) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Käyttäjä ID:llä " +
                            (updated.getKayttaja() != null ? updated.getKayttaja().getKayttaja_id() : "null") +
                            " ei löytynyt");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
                }
            })
            .orElse(ResponseEntity.notFound().build());
}

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
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