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

import com.example.ticketguru.model.LippuTyyppi;
import com.example.ticketguru.model.LippuTyyppiRepository;

import jakarta.validation.Valid;


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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<LippuTyyppi> createLippu(@Valid @RequestBody LippuTyyppi uusi) {
        LippuTyyppi tallennettu = lippuTyyppiRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LippuTyyppi> updateLippuTyyppi(@PathVariable Long id, @Valid @RequestBody LippuTyyppi updated) {
        return lippuTyyppiRepository.findById(id)
                .map(lippuTyyppi -> {
                    lippuTyyppi.setNimi(updated.getNimi());
                    lippuTyyppi.setHinta(updated.getHinta());
                    lippuTyyppi.setTapahtuma(updated.getTapahtuma());
                    LippuTyyppi saved = lippuTyyppiRepository.save(lippuTyyppi);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLippuTyyppi(@PathVariable Long id) {
        return lippuTyyppiRepository.findById(id)
                .map(lippuTyyppi -> {
                    lippuTyyppiRepository.delete(lippuTyyppi);
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
