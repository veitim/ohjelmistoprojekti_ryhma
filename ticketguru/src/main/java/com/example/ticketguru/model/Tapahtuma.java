package com.example.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    
    @Column(name = "jarjestaja_id")
    private long jarjestajaId;
    
    @Column(name = "alkamis_pvm")
    private LocalDate alkamisPvm;
    
    @Column(name = "paattymis_pvm")
    private LocalDate paattymisPvm;
    
    @Column(name = "lisatiedot", columnDefinition = "TEXT")
    private String lisatiedot;
    
    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lippu> liput;
    
    public Tapahtuma() {}
    
    public Tapahtuma(String nimi, String katuosoite, long jarjestajaId, 
                    LocalDate alkamisPvm, LocalDate paattymisPvm, 
                     String lisatiedot) {
        this.nimi = nimi;
        this.katuosoite = katuosoite;
        this.jarjestajaId = jarjestajaId;
        this.alkamisPvm = alkamisPvm;
        this.paattymisPvm = paattymisPvm;
        this.lisatiedot = lisatiedot;
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
    
    public long getJarjestajaId() {
        return jarjestajaId;
    }
    
    public void setJarjestajaId(long jarjestajaId) {
        this.jarjestajaId = jarjestajaId;
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
    
    @Override
    public String toString() {
        return "Tapahtuma{" +
                "tapahtuma_id=" + tapahtuma_id +
                ", nimi='" + nimi + '\'' +
                ", katuosoite='" + katuosoite + '\'' +
                ", jarjestajaId=" + jarjestajaId +
                ", alkamisPvm=" + alkamisPvm +
                ", paattymisPvm=" + paattymisPvm +
                ", lisatiedot='" + lisatiedot + '\'' +
                '}';
    }
}
