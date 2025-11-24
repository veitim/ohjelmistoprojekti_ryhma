package com.example.ticketguru.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface LippuRepository extends CrudRepository<Lippu, Long>{  
    Optional<Lippu> findByKoodi(String koodi);
    long countByLipputyyppi_Tapahtuma_TapahtumaId(Long tapahtumaId);
}
