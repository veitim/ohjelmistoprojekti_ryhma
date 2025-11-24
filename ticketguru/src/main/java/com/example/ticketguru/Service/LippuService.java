package com.example.ticketguru.Service;

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
                HttpStatus.CONFLICT,"Tapahtuma on täynnä, paikkoja ei ole jäljellä");
        }
 
        lippu.setLipputyyppi(lipputyyppi);
        lippu.setKoodi("a0000001");
        lippu.setKaytetty(false);

        return lippuRepository.save(lippu);
    }

}
