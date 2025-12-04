package com.example.ticketguru.Service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.LippuTyyppiRepository;
import com.example.ticketguru.model.MyyntiRepository;

@Service
public class LippuMyyntiService {

    private final LippuRepository lippuRepository;
    private final LippuTyyppiRepository lipputyyppiRepository;
    private final MyyntiRepository myyntiRepository;
    private final KayttajaRepository kayttajaRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public LippuMyyntiService(
        LippuRepository lippuRepository, 
        LippuTyyppiRepository lipputyyppiRepository, 
        MyyntiRepository myyntiRepository,
        KayttajaRepository kayttajaRepository
    ) {
        this.lippuRepository = lippuRepository;
        this.lipputyyppiRepository = lipputyyppiRepository;
        this.myyntiRepository = myyntiRepository;
        this.kayttajaRepository = kayttajaRepository;
    }

}
