package com.example.ticketguru.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/myynnit")
public class MyyntiRestController {
    
    private final MyyntiRepository repository;

    public MyyntiRestController(MyyntiRepository repository) {
        this.repository = repository;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Myynti create(@Valid @RequestBody Myynti myynti) {
        return repository.save(myynti);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Myynti> update(@PathVariable Long id, @Valid @RequestBody Myynti updated) {
        return repository.findById(id)
                .map(myynti -> {
                    myynti.setKayttaja(updated.getKayttaja());
                    myynti.setPaivamaara(updated.getPaivamaara());
                    myynti.setMaksutapa(updated.getMaksutapa());
                    myynti.setTyyppi(updated.getTyyppi());
                    myynti.setMyyntirivit(updated.getMyyntirivit());
                    return ResponseEntity.ok(repository.save(myynti));
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