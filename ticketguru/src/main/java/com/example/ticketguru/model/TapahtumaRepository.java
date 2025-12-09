package com.example.ticketguru.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TapahtumaRepository extends CrudRepository<Tapahtuma, Long> {
    Optional<Tapahtuma> findById(Long id);
}
