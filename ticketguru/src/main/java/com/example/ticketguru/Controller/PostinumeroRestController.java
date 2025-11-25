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

import com.example.ticketguru.model.Postinumero;
import com.example.ticketguru.model.PostinumeroRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/postinumerot")
public class PostinumeroRestController {

    private final PostinumeroRepository postinumeroRepository;

    public PostinumeroRestController(PostinumeroRepository postinumeroRepository) {
        this.postinumeroRepository = postinumeroRepository;
    }

    @GetMapping
    public List<Postinumero> getAllPostinumerot() {
        return (List<Postinumero>) postinumeroRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Postinumero> createPostinumero(@Valid @RequestBody Postinumero uusi) {
        Postinumero tallennettu = postinumeroRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Postinumero> updatePostinumero(@PathVariable String id, @Valid @RequestBody Postinumero updated) {
        return postinumeroRepository.findById(id)
                .map(postinumero -> {
                    postinumero.setPostitoimipaikka(updated.getPostitoimipaikka());
                    
                    Postinumero saved = postinumeroRepository.save(postinumero);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/ADMIN/{id}")
    public ResponseEntity<Void> deletePostinumero(@PathVariable String id) {
        return postinumeroRepository.findById(id)
                .map(postinumero -> {
                    postinumeroRepository.delete(postinumero);
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
 