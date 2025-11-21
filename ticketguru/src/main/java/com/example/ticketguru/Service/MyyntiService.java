package com.example.ticketguru.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.Myyntirivi;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MyyntiService {
    
    private final MyyntiRepository myyntiRepository;
    private final LippuRepository lippuRepository;
    private final KayttajaRepository kayttajaRepository;

    public MyyntiService(MyyntiRepository myyntiRepository, LippuRepository lippuRepository, KayttajaRepository kayttajaRepository) {
        this.myyntiRepository = myyntiRepository;
        this.lippuRepository = lippuRepository;
        this.kayttajaRepository = kayttajaRepository;
    }

    @Transactional
    public Myynti luoMyynti(Myynti myynti) {

        // Tänne pitää laittaa asiat siten, että service luo myytävät liput sekä myyntirivit. Liput sidotaan haluttuihin lipputyyppeihin ja myyntirivit yhteen myyntiin.

        if (myynti.getKayttaja() == null || 
        !kayttajaRepository.existsById(myynti.getKayttaja().getKayttaja_id())) {
        throw new EntityNotFoundException("Käyttäjää ei löytynyt annetulla ID:llä");
        }
        
        myynti.setPaivamaara(LocalDate.now());

        System.out.println("Myyntirivejä: " + myynti.getMyyntirivit().size());

        for (Myyntirivi r : myynti.getMyyntirivit()) {

            // Lippu lippu = lippuRepository.findById(r.getLippu().getLippu_id())
            //     .orElseThrow();

            // if (lippu.isTila()) {
            //     throw new IllegalStateException("Lippu on jo myyty!");
            // } 
            
            r.setMyynti(myynti);
                           
            // lippu.setTila(true);  TÄTÄ EI PITÄISI TARVITA, SILLÄ LIPPUJA EI LUODA ENNAKKOON!
            // lippuRepository.save(lippu);
        }

        return myyntiRepository.save(myynti);
    }
}
