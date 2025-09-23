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

@Entity
@Table(name = "tapahtuma")
public class Tapahtuma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tapahtuma_id;
    
    @Column(name = "nimi", nullable = false, length = 255)
    private String nimi;
    
    @Column(name = "katuosoite", length = 255)
    private String katuosoite;
    
    @Column(name = "alkamis_pvm")
    private LocalDate alkamisPvm;
    
    @Column(name = "paattymis_pvm")
    private LocalDate paattymisPvm;
    
    @Column(name = "lisatiedot", columnDefinition = "TEXT")
    private String lisatiedot;
    
    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lippu> liput;

    @JsonIgnoreProperties("tapahtumat")
    @ManyToOne
    @JoinColumn(name = "jarjestaja_id")
    private Jarjestaja jarjestaja;
    
    public Tapahtuma() {}
    
    public Tapahtuma(String nimi, String katuosoite,
                    LocalDate alkamisPvm, LocalDate paattymisPvm, 
                     String lisatiedot, Jarjestaja jarjestaja) {
        this.nimi = nimi;
        this.katuosoite = katuosoite;
        this.alkamisPvm = alkamisPvm;
        this.paattymisPvm = paattymisPvm;
        this.lisatiedot = lisatiedot;
        this.jarjestaja = jarjestaja;
    }
    
    public long getTapahtuma_id() {
        return tapahtuma_id;
    }
    
    public void setTapahtuma_id(long tapahtuma_id) {
        this.tapahtuma_id = tapahtuma_id;
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
    
    public List<Lippu> getLiput() {
        return liput;
    }
    
    public void setLiput(List<Lippu> liput) {
        this.liput = liput;
    }

    public Jarjestaja getJarjestaja() {
        return jarjestaja;
    }

    public void setJarjestaja(Jarjestaja jarjestaja) {
        this.jarjestaja = jarjestaja;
    }
    
    @Override
    public String toString() {
        return "Tapahtuma{" +
                "tapahtuma_id=" + tapahtuma_id +
                ", nimi='" + nimi + '\'' +
                ", katuosoite='" + katuosoite + '\'' +
                ", alkamisPvm=" + alkamisPvm +
                ", paattymisPvm=" + paattymisPvm +
                ", lisatiedot='" + lisatiedot + '\'' +
                ", jarjestaja=" + this.getJarjestaja() +
                '}';
    }
}
