package com.example.ticketguru.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.ticketguru.Dto.MyyntiDto;
import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.LippuTyyppi;
import com.example.ticketguru.model.LippuTyyppiRepository;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.MyyntiriviRepository;

import jakarta.transaction.Transactional;

@Service
public class LippuMyyntiService {

    private final LippuRepository lippuRepository;
    private final LippuTyyppiRepository lipputyyppiRepository;
    private final MyyntiRepository myyntiRepository;
    private final MyyntiriviRepository myyntiriviRepository;
    private final KayttajaRepository kayttajaRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public LippuMyyntiService(
        LippuRepository lippuRepository, 
        LippuTyyppiRepository lipputyyppiRepository, 
        MyyntiRepository myyntiRepository,
        MyyntiriviRepository myyntiriviRepository,
        KayttajaRepository kayttajaRepository
    ) {
        this.lippuRepository = lippuRepository;
        this.lipputyyppiRepository = lipputyyppiRepository;
        this.myyntiRepository = myyntiRepository;
        this.myyntiriviRepository = myyntiriviRepository;
        this.kayttajaRepository = kayttajaRepository;
    }

    @Transactional
    public Myynti createMyynti(MyyntiDto dto) {

        Kayttaja kayttaja = kayttajaRepository.findById(dto.getKayttaja_id())
                .orElseThrow(() -> new RuntimeException("Käyttäjää ei löytynyt"));

        LippuTyyppi tyyppi = lipputyyppiRepository.findById(dto.getTyyppi_id())
                .orElseThrow(() -> new RuntimeException("Lipputyyppiä ei löytynyt"));

        // 1. Luodaan myynti
        Myynti myynti = new Myynti();
        myynti.setKayttaja(kayttaja);
        myynti.setMaksutapa(dto.getMaksutapa());
        myynti.setPaivamaara(LocalDate.now());
        myyntiRepository.save(myynti);

        // 2. Luodaan myyntirivi
        MyyntiRivi rivi = new MyyntiRivi();
        rivi.setMyynti(myynti);
        rivi.setLippuTyyppi(tyyppi);
        rivi.setMaara(dto.getMaara());
        rivi.setHinta(tyyppi.getHinta() * dto.getMaara());
        myyntiriviRepository.save(rivi);

        // 3. Luodaan liput
        for (int i = 0; i < dto.getMaara(); i++) {
            Lippu lippu = new Lippu();
            lippu.setLippuTyyppi(tyyppi);
            lippu.setMyyntiRivi(rivi);
            lippu.setKoodi(generateRandomCode());
            lippuRepository.save(lippu);
        }

        return myynti;
    }
}
