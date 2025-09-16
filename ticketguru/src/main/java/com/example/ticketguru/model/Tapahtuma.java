package com.example.ticketguru.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Tapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tapahtuma_id;

    private String nimi;
    private String osoite;
    private double hinta;
    private String lisatieto;
    private LocalDateTime alkamispvm;
    private LocalDateTime paattymispvm;

    @JsonIgnoreProperties("tapahtuma")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tapahtuma")
    private List<Lippu> Liput;

    @ManyToOne
    @JoinColumn(name = "jarjestaja_id")
    private Jarjestaja jarjestaja;

    public Tapahtuma() {
        super();
    }

    public Tapahtuma(String nimi, String osoite, double hinta, String lisatieto, LocalDateTime alkamispvm,
            LocalDateTime paattymispvm, Jarjestaja jarjestaja) {
        this.nimi = nimi;
        this.osoite = osoite;
        this.hinta = hinta;
        this.lisatieto = lisatieto;
        this.alkamispvm = alkamispvm;
        this.paattymispvm = paattymispvm;
        this.jarjestaja = jarjestaja;
    }

    public Long getTapahtuma_id() {
        return tapahtuma_id;
    }

    public void setTapahtuma_id(Long tapahtuma_id) {
        this.tapahtuma_id = tapahtuma_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public String getLisatieto() {
        return lisatieto;
    }

    public void setLisatieto(String lisatieto) {
        this.lisatieto = lisatieto;
    }

    public LocalDateTime getAlkamispvm() {
        return alkamispvm;
    }

    public void setAlkamispvm(LocalDateTime alkamispvm) {
        this.alkamispvm = alkamispvm;
    }

    public LocalDateTime getPaattymispvm() {
        return paattymispvm;
    }

    public void setPaattymispvm(LocalDateTime paattymispvm) {
        this.paattymispvm = paattymispvm;
    }

    public List<Lippu> getLiput() {
        return Liput;
    }

    public void setLiput(List<Lippu> liput) {
        Liput = liput;
    }

    public Jarjestaja getJarjestaja() {
        return jarjestaja;
    }

    public void setJarjestaja(Jarjestaja jarjestaja) {
        this.jarjestaja = jarjestaja;
    }

    @Override
    public String toString() {
        return "Tapahtuma [tapahtuma_id=" + tapahtuma_id + ", nimi=" + nimi + ", osoite=" + osoite + ", hinta=" + hinta
                + ", lisatieto=" + lisatieto + ", alkamispvm=" + alkamispvm + ", paattymispvm=" + paattymispvm
                + ", jarjestaja=" + jarjestaja + "]";
    }

}
