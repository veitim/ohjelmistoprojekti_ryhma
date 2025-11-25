package com.example.ticketguru.Service;

import java.security.SecureRandom;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.LippuTyyppi;
import com.example.ticketguru.model.LippuTyyppiRepository;
import com.example.ticketguru.model.Tapahtuma;

import jakarta.transaction.Transactional;

@Service
public class LippuService {

    private final LippuRepository lippuRepository;
    private final LippuTyyppiRepository lipputyyppiRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public LippuService(LippuRepository lippuRepository, LippuTyyppiRepository lipputyyppiRepository) {
        this.lippuRepository = lippuRepository;
        this.lipputyyppiRepository = lipputyyppiRepository;
    }

    @Transactional
    public Lippu luoLippu(Lippu lippu) {

        LippuTyyppi lipputyyppi = lipputyyppiRepository.findById(
            lippu.getLipputyyppi().getTyyppi_id())
                .orElseThrow(() -> new RuntimeException("Lipputyyppi ei löydy"));

        Tapahtuma tapahtuma = lipputyyppi.getTapahtuma();
        long liput = lippuRepository.countByLipputyyppi_Tapahtuma_TapahtumaId(tapahtuma.getTapahtumaId());

        if (liput >= tapahtuma.getPaikkamaara()) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,"Tapahtumaan ei ole myytäviä lippuja enään jäljellä");
        }
 
        lippu.setLipputyyppi(lipputyyppi);
        lippu.setKaytetty(false);

        String generatedCode = generateTicketCode();
        while (lippuRepository.findByKoodi(generatedCode).isPresent()) {
            generatedCode = generateTicketCode();
        }
        lippu.setKoodi(generatedCode);
        
        return lippuRepository.save(lippu);
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
