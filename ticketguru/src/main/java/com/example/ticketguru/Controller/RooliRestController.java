package com.example.ticketguru.Controller;

import java.util.HashMap;
import java.util.Map;


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


import com.example.ticketguru.model.Rooli;
import com.example.ticketguru.model.RooliRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/roolit")
public class RooliRestController {

    private final RooliRepository repository;

    public RooliRestController(RooliRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Rooli> getAllRoolit() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rooli> getRooliById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rooli createRooli(@Valid @RequestBody Rooli rooli) {
        return repository.save(rooli);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rooli> updateRooli(@PathVariable Long id, @Valid @RequestBody Rooli updatedRooli) {
        return repository.findById(id)
                .map(rooli -> {
                    rooli.setName(updatedRooli.getName());
                    return ResponseEntity.ok(repository.save(rooli));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRooli(@PathVariable Long id) {
        return repository.findById(id)
                .map(rooli -> {
                    repository.delete(rooli);
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

