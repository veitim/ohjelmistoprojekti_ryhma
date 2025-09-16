package com.example.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Jarjestaja {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long jarjestaja_id;

    private String nimi;
    private String yhteyshenkilo;
    private String sahkoposti;
    private String puhelin;

    @OneToMany
    private List<Tapahtuma> Tapahtumat;

}
