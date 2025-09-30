package com.example.ticketguru.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Postinumero {

    @Id
    private String postinumero;

    @Column(name = "postitoimipaikka", length = 255)
    private String postitoimipaikka;

    @JsonIgnoreProperties("postinumero")
    @OneToMany(mappedBy= "postinumero", cascade= CascadeType.ALL)
    private List<Kayttaja> kayttajat;

    public Postinumero() {}

    public Postinumero(String postinumero, String postitoimipaikka) {
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }

    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    public List<Kayttaja> getKayttajat() {
        return kayttajat;
    }

    public void setAsiakkaat(List<Kayttaja> kayttajat) {
        this.kayttajat = kayttajat;
    }

    @Override
    public String toString() {
        return "Postinumero [postinumero=" + postinumero + ", postitoimipaikka=" + postitoimipaikka + ", kayttajat="
                + kayttajat + "]";
    }
    

}
