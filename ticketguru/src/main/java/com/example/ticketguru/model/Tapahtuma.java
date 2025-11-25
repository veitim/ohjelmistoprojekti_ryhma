package com.example.ticketguru.model;

import java.time.LocalDate;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tapahtuma")
public class Tapahtuma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tapahtumaId;
    
    @NotBlank(message = "Tapahtumalla täytyy olla nimi")
    @Size(min = 1, max = 150, message = "Tapahtuman nimi saa olla enintään 150 merkkiä pitkä")
    @Column(name = "nimi", nullable = false, length = 150)
    private String nimi;
    
    @NotBlank(message = "Tapahtumalla täytyy olla sijainti")
    @Size(min = 1, max = 150, message = "Tapahtuman sijainti saa olaa enintään 150 merkkiä pitkä")
    @Column(name = "katuosoite", nullable = false, length = 150)
    private String katuosoite;
    
    @NotNull(message = "Tapahtumalla täytyy olla alkamisen ajankohta")
    @NotNull(message = "Tapahtuman alkamispäivä ei voi olla menneisyydessä")
    @Column(name = "alkamis_pvm", nullable = false)
    private LocalDate alkamisPvm;
    
    @NotNull(message = "Tapahtumalla täytyy olla päättymisen ajankohta")
    @NotNull(message = "Tapahtuman päättymispäivä ei voi olla menneisyydessä")
    @Column(name = "paattymis_pvm", nullable = false)
    private LocalDate paattymisPvm;
    
    @Column(name = "lisatiedot", columnDefinition = "TEXT")
    private String lisatiedot;

    @NotNull(message = "Tapahtumalla täytyy olla paikkamäärä")
    @PositiveOrZero(message = "Paikkamäärää ei voi olla negatiivinen")
    @Column(name = "paikkamaara")
    private int paikkamaara;
    
    @OneToMany(mappedBy = "tapahtuma", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private List<LippuTyyppi> lipputyyppi;

    @NotNull(message = "Tapahtumalla täytyy olla järjestäjä")
    @JsonIgnoreProperties("tapahtumat")
    @ManyToOne
    @JoinColumn(name = "jarjestaja_id")
    private Jarjestaja jarjestaja;
    
    public Tapahtuma() {}
    
    public Tapahtuma(String nimi, String katuosoite,
                    LocalDate alkamisPvm, LocalDate paattymisPvm, 
                     String lisatiedot, Jarjestaja jarjestaja, int paikkamaara) {
        this.nimi = nimi;
        this.katuosoite = katuosoite;
        this.alkamisPvm = alkamisPvm;
        this.paattymisPvm = paattymisPvm;
        this.lisatiedot = lisatiedot;
        this.jarjestaja = jarjestaja;
        this.paikkamaara = paikkamaara;
    }
    
    public long getTapahtumaId() {
        return tapahtumaId;
    }
    
    public void setTapahtumaId(long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public String getKatuosoite() {
        return katuosoite;
    }
    
    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }
    
    public LocalDate getAlkamisPvm() {
        return alkamisPvm;
    }
    
    public void setAlkamisPvm(LocalDate alkamisPvm) {
        this.alkamisPvm = alkamisPvm;
    }
    
    public LocalDate getPaattymisPvm() {
        return paattymisPvm;
    }
    
    public void setPaattymisPvm(LocalDate paattymisPvm) {
        this.paattymisPvm = paattymisPvm;
    }
     
    public String getLisatiedot() {
        return lisatiedot;
    }
    
    public void setLisatiedot(String lisatiedot) {
        this.lisatiedot = lisatiedot;
    }
    
    public Jarjestaja getJarjestaja() {
        return jarjestaja;
    }

    public void setJarjestaja(Jarjestaja jarjestaja) {
        this.jarjestaja = jarjestaja;
    }

    public int getPaikkamaara() {
        return paikkamaara;
    }

    public void setPaikkamaara(int paikkamaara) {
        this.paikkamaara = paikkamaara;
    }
    
    public List<LippuTyyppi> getLipputyyppi() {
        return lipputyyppi;
    }

    public void setLipputyyppi(List<LippuTyyppi> lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }

    @Override
    public String toString() {
        return "Tapahtuma{" +
                "tapahtumaId=" + tapahtumaId +
                ", nimi='" + nimi + '\'' +
                ", katuosoite='" + katuosoite + '\'' +
                ", alkamisPvm=" + alkamisPvm +
                ", paattymisPvm=" + paattymisPvm +
                ", lisatiedot='" + lisatiedot + '\'' +
                ", paikkamaara='" + paikkamaara + '\'' +
                ", jarjestaja=" + this.getJarjestaja() +
                '}';
    }
}
