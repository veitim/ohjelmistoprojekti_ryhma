package com.example.ticketguru.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.Myyntirivi;
import com.example.ticketguru.model.MyyntiriviRepository;

@RestController
@RequestMapping("api/myyntirivit")
public class MyyntiriviRestController {

    private final MyyntiriviRepository myyntiriviRepository;
    private final MyyntiRepository myyntiRepository;
    private final LippuRepository lippuRepository;

    public MyyntiriviRestController(MyyntiriviRepository myyntiriviRepository,
            MyyntiRepository myyntiRepository,
            LippuRepository lippuRepository) {
        this.myyntiriviRepository = myyntiriviRepository;
        this.myyntiRepository = myyntiRepository;
        this.lippuRepository = lippuRepository;
    }

    @GetMapping
    public List<Myyntirivi> getAllMyyntirivit() {
        return (List<Myyntirivi>) myyntiriviRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Myyntirivi> getMyyntiriviById(@PathVariable Long id) {
        return myyntiriviRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Myyntirivi> createMyyntirivi(@RequestBody Myyntirivi uusi) {
        if (uusi.getMyynti() == null || uusi.getMyynti().getMyyntiId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (uusi.getLippu() == null) {
            return ResponseEntity.badRequest().build();
        }

        Myynti myynti = myyntiRepository.findById(uusi.getMyynti().getMyyntiId())
                .orElse(null);
        if (myynti == null) {
            return ResponseEntity.notFound().build();
        }

        Lippu lippu = lippuRepository.findById(uusi.getLippu().getLippu_id())
                .orElse(null);
        if (lippu == null) {
            return ResponseEntity.notFound().build();
        }

        uusi.setMyynti(myynti);
        uusi.setLippu(lippu);

        Myyntirivi tallennettu = myyntiriviRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

@PreAuthorize("hasAuthority('ADMIN')")
@PutMapping("/{id}")
public ResponseEntity<Myyntirivi> updateMyyntirivi(@PathVariable Long id, @RequestBody Myyntirivi updated) {
    if (updated.getMyynti() == null || updated.getMyynti().getMyyntiId() == null) {
        return ResponseEntity.badRequest().build();
    }
    
    if (updated.getLippu() == null) {
        return ResponseEntity.badRequest().build();
    }
    
    Myyntirivi myyntirivi = myyntiriviRepository.findById(id).orElse(null);
    if (myyntirivi == null) {
        return ResponseEntity.notFound().build();
    }
    
    Myynti myynti = myyntiRepository.findById(updated.getMyynti().getMyyntiId())
        .orElse(null);
    if (myynti == null) {
        return ResponseEntity.notFound().build();
    }
    
    Lippu lippu = lippuRepository.findById(updated.getLippu().getLippu_id())
        .orElse(null);
    if (lippu == null) {
        return ResponseEntity.notFound().build();
    }
    
    myyntirivi.setMyynti(myynti);
    myyntirivi.setLippu(lippu);
    
    Myyntirivi saved = myyntiriviRepository.save(myyntirivi);
    return ResponseEntity.ok(saved);
}

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMyyntirivi(@PathVariable Long id) {
        return myyntiriviRepository.findById(id)
                .map(myyntirivi -> {
                    myyntiriviRepository.delete(myyntirivi);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
