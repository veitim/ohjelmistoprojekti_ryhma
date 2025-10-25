package com.example.ticketguru.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Postinumero {

    @Id
    @Size(min = 5, max = 5, message = "Postinumero voi olla vain 5 merkkiä pitkä.")
    @Pattern(regexp = "^[0-9]{5}$", message = "Postinumeron on oltava 5 numeroa.")
    @Column(name = "Postinumero", nullable= false)
    private String postinumero;


    @Column(name = "postitoimipaikka", nullable= false, length = 100)
    @NotNull(message = "Postitoimipaikka on pakollinen tieto")
    @Size(min = 1, max = 50, message = "Postitoimipaikan pitää olla 1-50 merkkiä pitkä.")
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
