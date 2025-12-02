package com.example.ticketguru.Controller;

import java.util.HashMap;
import java.util.List;
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

import com.example.ticketguru.model.Tapahtuma;
import com.example.ticketguru.model.TapahtumaRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/tapahtumat")
public class TapahtumaRestController {

    private final TapahtumaRepository tapahtumaRepository;

    public TapahtumaRestController(TapahtumaRepository tapahtumaRepository) {
        this.tapahtumaRepository = tapahtumaRepository;
    }

    @GetMapping
    public List<Tapahtuma> getAllTapahtumat() {
        return (List<Tapahtuma>) tapahtumaRepository.findAll();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tapahtuma> getLippuById(@PathVariable Long id) {
        return tapahtumaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Tapahtuma> createTapahtuma(@Valid @RequestBody Tapahtuma uusi) {
        Tapahtuma tallennettu = tapahtumaRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Tapahtuma> updateTapahtuma(@PathVariable Long id, @Valid @RequestBody Tapahtuma updated) {
        return tapahtumaRepository.findById(id)
                .map(tapahtuma -> {
                    tapahtuma.setNimi(updated.getNimi());
                    tapahtuma.setKatuosoite(updated.getKatuosoite());
                    tapahtuma.setJarjestaja(updated.getJarjestaja());
                    tapahtuma.setAlkamisPvm(updated.getAlkamisPvm());
                    tapahtuma.setPaattymisPvm(updated.getPaattymisPvm());
                    tapahtuma.setLisatiedot(updated.getLisatiedot());
                    tapahtuma.setPaikkamaara(updated.getPaikkamaara());

                    Tapahtuma saved = tapahtumaRepository.save(tapahtuma);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTapahtuma(@PathVariable Long id) {
        return tapahtumaRepository.findById(id)
                .map(tapahtuma -> {
                    tapahtumaRepository.delete(tapahtuma);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
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
