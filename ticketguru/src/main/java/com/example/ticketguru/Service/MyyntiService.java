package com.example.ticketguru.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.Myyntirivi;

import jakarta.transaction.Transactional;

@Service
public class MyyntiService {
    
    private final MyyntiRepository myyntiRepository;
    private final LippuRepository lippuRepository;

    public MyyntiService(MyyntiRepository myyntiRepository, LippuRepository lippuRepository) {
        this.myyntiRepository = myyntiRepository;
        this.lippuRepository = lippuRepository;
    }

    @Transactional
    public Myynti luoMyynti(Myynti myynti) {
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
