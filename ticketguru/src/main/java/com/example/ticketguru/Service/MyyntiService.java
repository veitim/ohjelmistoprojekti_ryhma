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

        double summa = 0.0;
        for (Myyntirivi r : myynti.getMyyntirivit()) {

            Lippu lippu = lippuRepository.findById(r.getLippu().getLippu_id())
                .orElseThrow();

            r.setLippu(lippu);

            if (lippu.getLipputyyppi() != null) {
            double hinta = lippu.getLipputyyppi().getHinta();
            // System.out.println("HINTA: " + hinta);
            summa += hinta;
            }

            r.setMyynti(myynti);
        }

        myynti.setSumma(summa);
        myynti.setPaivamaara(LocalDate.now());
        return myyntiRepository.save(myynti);
    }
}
