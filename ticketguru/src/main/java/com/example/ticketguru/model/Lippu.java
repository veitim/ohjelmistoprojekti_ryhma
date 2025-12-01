package com.example.ticketguru.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Lippu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lippu_id;

    @Size(max = 30, message = "Paikan nimi saa olla enintään 30 merkkiä")
    @Column(name = "paikka", length = 30)
    private String paikka;

    @Column(name = "kaytetty", nullable = false)
    private boolean kaytetty;

    //@NotBlank(message = "Lipulla täytyy olla koodi")
    @Column(name = "koodi", nullable = false)
    private String koodi;

    @NotNull(message = "Lipulla täytyy olla lipputyyppi")
    @JsonIgnoreProperties("lippu")
    @ManyToOne
    @JoinColumn(nullable = false)
    private LippuTyyppi lipputyyppi;

    @JsonIgnore
    @OneToMany(mappedBy= "lippu", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private List<Myyntirivi> myyntirivit;

    public Lippu() {
        super();
    }

    public Lippu(String paikka, boolean kaytetty, LippuTyyppi lipputyyppi, String koodi) {
        super();
        this.paikka = paikka;
        this.kaytetty = kaytetty;
        this.lipputyyppi = lipputyyppi;
        this.koodi = koodi;
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

    public boolean isKaytetty() {
        return kaytetty;
    }

    public void setKaytetty(boolean kaytetty) {
        this.kaytetty = kaytetty;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public List<Myyntirivi> getMyyntirivit() {
        return myyntirivit;
    }

    public void setMyyntirivit(List<Myyntirivi> myyntirivit) {
        this.myyntirivit = myyntirivit;
    }

    public LippuTyyppi getLipputyyppi() {
        return lipputyyppi;
    }

    public void setLipputyyppi(LippuTyyppi lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }


    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    @Override
    public String toString() {
        return "Lippu [lippu_id=" + lippu_id + 
        ", paikka=" + paikka + 
        ", kaytetty=" + kaytetty +
        ", lipputyyppi=" + this.getLipputyyppi() +
        ", koodi=" + koodi + "]";
    }

}
