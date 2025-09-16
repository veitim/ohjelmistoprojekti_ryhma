package com.example.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;
import java.math.BigDecimal;
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
    
    @Column(name = "hinta", precision = 10, scale = 2)
    private BigDecimal hinta;
    
    @Column(name = "lisatiedot", columnDefinition = "TEXT")
    private String lisatiedot;
    
    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lippu> liput;
    
    public Tapahtuma() {}
    
    public Tapahtuma(String nimi, String katuosoite, long jarjestajaId, 
                    LocalDate alkamisPvm, LocalDate paattymisPvm, 
                    BigDecimal hinta, String lisatiedot) {
        this.nimi = nimi;
        this.katuosoite = katuosoite;
        this.jarjestajaId = jarjestajaId;
        this.alkamisPvm = alkamisPvm;
        this.paattymisPvm = paattymisPvm;
        this.hinta = hinta;
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
    
    public BigDecimal getHinta() {
        return hinta;
    }
    
    public void setHinta(BigDecimal hinta) {
        this.hinta = hinta;
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
                ", hinta=" + hinta +
                ", lisatiedot='" + lisatiedot + '\'' +
                '}';
    }
}
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
