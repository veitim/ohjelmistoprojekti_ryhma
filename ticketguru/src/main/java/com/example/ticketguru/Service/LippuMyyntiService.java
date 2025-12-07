package com.example.ticketguru.Service;

import java.security.SecureRandom;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ticketguru.Dto.MyyntiDto;
import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.LippuTyyppi;
import com.example.ticketguru.model.LippuTyyppiRepository;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.Myyntirivi;
import com.example.ticketguru.model.MyyntiriviRepository;
import com.example.ticketguru.model.Tapahtuma;

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

        // Tämä tekee myynnin
        Myynti myynti = new Myynti();
        myynti.setKayttaja(kayttaja);
        myynti.setMaksutapa(dto.getMaksutapa());
        myynti.setPaivamaara(LocalDate.now());
        myyntiRepository.save(myynti);

        double summa = 0.0;

        for (var riviDto : dto.getRivit()) {

            // Hae lipputyyppi
            LippuTyyppi tyyppi = lipputyyppiRepository.findById(riviDto.getTyyppi_id())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Lipputyyppiä ei löytynyt"));

            Tapahtuma tapahtuma = tyyppi.getTapahtuma();

            // Tarkista vapaat paikat
            long nykyiset = lippuRepository.countByLipputyyppi_Tapahtuma_TapahtumaId(tapahtuma.getTapahtumaId());
            if (nykyiset + riviDto.getMaara() > tapahtuma.getPaikkamaara()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Tapahtumaan ei ole tarpeeksi vapaita lippuja: " + tapahtuma.getNimi());
            }

            for (int i = 0; i < riviDto.getMaara(); i++) {

                // Tämä tekee liput   
                Lippu lippu = new Lippu();
                lippu.setLipputyyppi(tyyppi);
                
                // Tämä Tarkistaa, että samaa lippukoodia ei ole olemassa.
                String generatedCode = generateTicketCode();
                while (lippuRepository.findByKoodi(generatedCode).isPresent()) {
                    generatedCode = generateTicketCode();
                }
                lippu.setKoodi(generatedCode);
                lippuRepository.save(lippu);
            

                // Tämä tekee myyntirivit
                Myyntirivi rivi = new Myyntirivi();
                rivi.setMyynti(myynti);
                rivi.setLippu(lippu);
                myyntiriviRepository.save(rivi);

                summa += tyyppi.getHinta();
            }
        }

        myynti.setSumma(summa);
        return myynti;
    }

    private String generateTicketCode() {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

}
