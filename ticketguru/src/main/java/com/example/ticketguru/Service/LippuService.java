package com.example.ticketguru.Service;

import org.springframework.stereotype.Service;

import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.LippuTyyppi;
import com.example.ticketguru.model.LippuTyyppiRepository;

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

            // Haetaan lipputyyppi
            LippuTyyppi lipputyyppi = lipputyyppiRepository.findById(
                    lippu.getLipputyyppi().getTyyppi_id())
                    .orElseThrow(() -> new RuntimeException("Lipputyyppi ei löydy"));
 
            lippu.setLipputyyppi(lipputyyppi);
            lippu.setKoodi("a0000001");
            lippu.setKaytetty(false);

        return lippuRepository.save(lippu);
    }

}
