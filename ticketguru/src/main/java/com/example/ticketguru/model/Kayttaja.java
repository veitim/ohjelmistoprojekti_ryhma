package com.example.ticketguru.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long kayttaja_id;
    
    @Column(name = "etunimi", length = 255)
    private String etunimi;

    @Column(name = "sukunimi", length = 255)
    private String sukunimi;
    
    @Column(name = "katuosoite", length = 255)
    private String katuosoite;

    @Column(name = "syntymaaika", length = 255)
    private String syntymaaika;

    @Column(name = "sahkoposti", length = 255)
    private String sahkoposti;

    @Column(name = "puhelinnro", length = 255)
    private String puhelinnro;

    @Column(name = "lisatieto", length = 255)
    private String lisatieto;

    @JsonIgnoreProperties("kayttajat")
    @ManyToOne
    @JoinColumn(name= "postinumero")
    private Postinumero postinumero;

    @JsonIgnoreProperties("kayttajat")
    @ManyToOne
    @JoinColumn(name= "rooli_id")
    private Rooli rooli;


    public Kayttaja() {
    }

    public Kayttaja(String etunimi, String sukunimi, String katuosoite, String sahkoposti, String puhelinnro,
            String lisatieto, String syntymaaika, Postinumero postinumero) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.katuosoite = katuosoite;
        this.syntymaaika = syntymaaika;
        this.sahkoposti = sahkoposti;
        this.puhelinnro = puhelinnro;
        this.lisatieto = lisatieto;
        this.postinumero = postinumero;
    }

    public long getKayttaja_id() {
        return kayttaja_id;
    }

    public void setKayttaja_id(long kayttaja_id) {
        this.kayttaja_id = kayttaja_id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getPuhelinnro() {
        return puhelinnro;
    }

    public void setPuhelinnro(String puhelinnro) {
        this.puhelinnro = puhelinnro;
    }

    public String getLisatieto() {
        return lisatieto;
    }

    public void setLisatieto(String lisatieto) {
        this.lisatieto = lisatieto;
    }

    public String getSyntymaaika() {
        return syntymaaika;
    }

    public void setSyntymaaika(String syntymaaika) {
        this.syntymaaika = syntymaaika;
    }

    public Postinumero getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(Postinumero postinumero) {
        this.postinumero = postinumero;
    }

    @Override
    public String toString() {
        return "kayttaja [kayttaja_id=" + kayttaja_id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", katuosoite="
                + katuosoite + ", syntymaaika=" + syntymaaika + ", sahkoposti=" + sahkoposti + ", puhelinnro="
                + puhelinnro + ", lisatieto=" + lisatieto + ", postinumero=" + postinumero +"]";
    }



    
}
