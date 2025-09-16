package com.example.ticketguru.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lippu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lippu_id;

    @Column(name = "paikka", nullable = false, length = 255)
    private String paikka;

    @Column(name = "tila")
    private boolean tila;

    @JsonIgnoreProperties("liput")
    @ManyToOne
    @JoinColumn(name = "tapahtuma_id")
    private Tapahtuma tapahtuma;

    @JsonIgnoreProperties("lippu")
    @OneToMany(mappedBy= "lippu", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Myyntirivi> myyntirivit;

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

    public List<Myyntirivi> getMyyntirivit() {
        return myyntirivit;
    }

    public void setMyyntirivit(List<Myyntirivi> myyntirivit) {
        this.myyntirivit = myyntirivit;
    }

    @Override
    public String toString() {
        return "Lippu [lippu_id=" + lippu_id + ", paikka=" + paikka + ", tila=" + tila + ", tapahtuma=" + tapahtuma
                + "]";
    }

}
