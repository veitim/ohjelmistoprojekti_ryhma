package com.example.ticketguru.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RooliRepository extends CrudRepository<Rooli, Long> {

     Optional<Rooli> findByName(Rooli title);

}
