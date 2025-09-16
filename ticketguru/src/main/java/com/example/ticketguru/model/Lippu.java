package com.example.ticketguru.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Lippu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lippu_id;

    private String paikka;
    private boolean tila;

    @JsonIgnoreProperties("liput")
    @ManyToOne
    @JoinColumn(name = "tapahtuma_id")
    private Tapahtuma tapahtuma;

    public Lippu() {
        super();
    }

    public Lippu(String paikka, boolean tila, Tapahtuma tapahtuma) {
        super();
        this.paikka = paikka;
        this.tila = tila;
        this.tapahtuma = tapahtuma;
    }

    public long getLippu_id() {
        return lippu_id;
    }

    public void setLippu_id(long lippu_id) {
        this.lippu_id = lippu_id;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public boolean isTila() {
        return tila;
    }

    public void setTila(boolean tila) {
        this.tila = tila;
    }

    public Tapahtuma getTapahtuma() {
        return tapahtuma;
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }

    @Override
    public String toString() {
        return "Lippu [lippu_id=" + lippu_id + ", paikka=" + paikka + ", tila=" + tila + ", tapahtuma=" + tapahtuma
                + "]";
    }

}
