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
import jakarta.persistence.OneToMany;

@Entity
public class LippuTyyppi {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long tyyppi_id;

    @Column(name = "nimi", length = 150)
    private String nimi;

    @Column(name = "hinta")
    private double hinta;

    @JsonIgnoreProperties("lipputyyppi")
    @OneToMany(mappedBy= "lipputyyppi", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Lippu> liput;

    public LippuTyyppi() {
    }

    public LippuTyyppi(String nimi, double hinta) {
        this.nimi = nimi;
        this.hinta = hinta;
    }

    public long getTyyppi_id() {
        return tyyppi_id;
    }

    public void setTyyppi_id(long tyyppi_id) {
        this.tyyppi_id = tyyppi_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public List<Lippu> getLiput() {
        return liput;
    }

    public void setLiput(List<Lippu> liput) {
        this.liput = liput;
    }

    @Override
    public String toString() {
        return "LippuTyyppi [tyyppi_id=" + tyyppi_id + ", nimi=" + nimi + ", hinta=" + hinta + "]";
    }

}
