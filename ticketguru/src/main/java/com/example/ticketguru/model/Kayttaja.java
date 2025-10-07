package com.example.ticketguru.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long kayttaja_id;
    
    @NotBlank(message = "Käyttäjällä pitää olla etunimi")
    @Size(min = 1, max = 50, message = "Etunimen täytyy olla 1-50 merkkiä pitkä.")
    @Column(name = "etunimi", length = 50, nullable = false)
    private String etunimi;

    @NotBlank(message = "Käyttäjällä pitää olla sukunimi")
    @Size(min = 1, max = 50, message = "sukunimen täytyy olla 1-50 merkkiä pitkä.")
    @Column(name = "sukunimi", length = 50, nullable = false)
    private String sukunimi;
    
    @Size(min = 1, max = 150, message = "Katuosoite voi olla vain 1-150 merkkiä pitkä.")
    @Column(name = "katuosoite", length = 150, nullable = false)
    private String katuosoite;

    @Column(name = "syntymaaika", length = 50)
    private LocalDate syntymaaika;

    @Email(message = "Sähköpostiosoite ei ole kelvollinen")
    @Size(min = 1, max = 100, message = "Sähköpostin täytyy olla 1-100 merkkiä pitkä")
    @Column(name = "sahkoposti", length = 100)
    private String sahkoposti;

    @Pattern(regexp = "^[0-9+\\-()\\s]*$", message = "Puhelinnumero sisältää virheellisiä merkkejä")
    @Column(name = "puhelinnro", length = 15)
    private String puhelinnro;

    @Column(name = "lisatieto", length = 500)
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

    public Kayttaja(String etunimi, String sukunimi, String katuosoite, String syntymaaika, String sahkoposti,
            String puhelinnro, String lisatieto, Postinumero postinumero, Rooli rooli) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.katuosoite = katuosoite;
        this.syntymaaika = syntymaaika;
        this.sahkoposti = sahkoposti;
        this.puhelinnro = puhelinnro;
        this.lisatieto = lisatieto;
        this.postinumero = postinumero;
        this.rooli = rooli;
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

    public Rooli getRooli() {
        return rooli;
    }

    public void setRooli(Rooli rooli) {
        this.rooli = rooli;
    }

    @Override
    public String toString() {
        return "kayttaja [kayttaja_id=" + kayttaja_id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", katuosoite="
                + katuosoite + ", syntymaaika=" + syntymaaika + ", sahkoposti=" + sahkoposti + ", puhelinnro="
                + puhelinnro + ", lisatieto=" + lisatieto + ", postinumero=" + postinumero + ", rooli=" + rooli + "]";
    }
 
}
