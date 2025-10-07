package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping
    public ResponseEntity<Postinumero> createPostinumero(@Valid @RequestBody Postinumero uusi) {
        Postinumero tallennettu = postinumeroRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Postinumero> updatePostinumero(@Valid @PathVariable String id, @RequestBody Postinumero updated) {
        return postinumeroRepository.findById(id)
                .map(postinumero -> {
                    postinumero.setPostitoimipaikka(updated.getPostitoimipaikka());
                    
                    Postinumero saved = postinumeroRepository.save(postinumero);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostinumero(@PathVariable String id) {
        return postinumeroRepository.findById(id)
                .map(postinumero -> {
                    postinumeroRepository.delete(postinumero);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
 