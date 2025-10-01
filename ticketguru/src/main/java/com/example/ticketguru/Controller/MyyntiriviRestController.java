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

import com.example.ticketguru.model.Myyntirivi;
import com.example.ticketguru.model.MyyntiriviRepository;

@RestController
@RequestMapping("api/myyntirivit")
public class MyyntiriviRestController {

    private final MyyntiriviRepository myyntiriviRepository;

    public MyyntiriviRestController(MyyntiriviRepository myyntiriviRepository) {
        this.myyntiriviRepository = myyntiriviRepository;
    }

    @GetMapping
    public List<Myyntirivi> getAllMyyntirivit() {
        return (List<Myyntirivi>)myyntiriviRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Myyntirivi> getMyyntiriviById(@PathVariable Long id) {
        return myyntiriviRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Myyntirivi> createMyyntirivi(@RequestBody Myyntirivi uusi) {
        Myyntirivi tallennettu = myyntiriviRepository.save(uusi);
        return ResponseEntity.ok(tallennettu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Myyntirivi> updateMyyntirivi(@PathVariable Long id, @RequestBody Myyntirivi updated) {
        return myyntiriviRepository.findById(id)
                .map(myyntirivi -> {
                    myyntirivi.setMyynti(updated.getMyynti());
                    myyntirivi.setLippu(updated.getLippu());
                    myyntirivi.setPaivamaara(updated.getPaivamaara());
                    myyntirivi.setSumma(updated.getSumma());

                    Myyntirivi saved = myyntiriviRepository.save(myyntirivi);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

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
