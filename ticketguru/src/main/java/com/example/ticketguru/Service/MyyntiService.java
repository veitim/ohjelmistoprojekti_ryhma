package com.example.ticketguru.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.Lippu;
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

        if (myynti.getKayttaja() == null || 
        !kayttajaRepository.existsById(myynti.getKayttaja().getKayttaja_id())) {
        throw new EntityNotFoundException("Käyttäjää ei löytynyt annetulla ID:llä");
        }

        // Asetetaan myyntipäivämäärä
        myynti.setPaivamaara(LocalDate.now());

        System.out.println("Myyntirivejä: " + myynti.getMyyntirivit().size());
        // Kytketään rivit tähän myyntiin
        for (Myyntirivi r : myynti.getMyyntirivit()) {
            r.setMyynti(myynti);

            // Päivitä lipun tila myydyksi
            Lippu lippu = lippuRepository.findById(r.getLippu().getLippu_id())
                                   .orElseThrow();
            lippu.setTila(true);
            lippuRepository.save(lippu);
        }

        return myyntiRepository.save(myynti);
    }
}
