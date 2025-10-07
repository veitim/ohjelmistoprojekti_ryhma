package com.example.ticketguru.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PostinumeroRepository extends CrudRepository<Postinumero, String>{

    Optional<Postinumero> findByPostinumero(String title);

}
