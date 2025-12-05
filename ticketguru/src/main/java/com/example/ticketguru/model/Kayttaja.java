package com.example.ticketguru.model;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long kayttaja_id;
    
    @NotBlank(message = "Käyttäjällä pitää olla etunimi")
    @Size(min = 1, max = 50, message = "Etunimi voi olla enintään 50 merkkiä pitkä.")
    @Column(name = "etunimi", length = 50, nullable = false)
    private String etunimi;

    @NotBlank(message = "Käyttäjällä pitää olla sukunimi")
    @Size(min = 1, max = 50, message = "sukunimi voi olla enintään 50 merkkiä pitkä.")
    @Column(name = "sukunimi", length = 50, nullable = false)
    private String sukunimi;

    @NotBlank(message = "Katuosoite on pakollinen")
    @Size(min = 1, max = 150, message = "Katuosoite voi olla enintään 150 merkkiä pitkä.")
    @Column(name = "katuosoite", length = 150, nullable = false)
    private String katuosoite;

    @NotNull(message = "Syntymäaika on pakollinen")
    @PastOrPresent(message = "Syntymäaika ei voi olla tulevaisuudessa")
    @Column(name = "syntymaaika", nullable = false)
    private LocalDate syntymaaika;

    @NotBlank(message = "Sähköpostiosoite on pakollinen")
    @Email(message = "Sähköpostiosoite ei ole kelvollinen")
    @Size(min = 1, max = 100, message = "Sähköposti voi olla enintään 100 merkkiä pitkä")
    @Column(name = "sahkoposti", length = 100, nullable = false)
    private String sahkoposti;

    @Pattern(regexp = "^[0-9+\\-()\\s]*$", message = "Puhelinnumero sisältää virheellisiä merkkejä")
    @Column(name = "puhelinnro", length = 15)
    @Size(max = 15, message = "puhelinumero on enintään 15 merkkiä pitkä")
    private String puhelinnro;

    @Column(name = "lisatieto", length = 500)
    private String lisatieto;

    @NotNull(message = "Postinumero on pakollinen")
    @JsonIgnoreProperties("kayttajat")
    @ManyToOne
    @JoinColumn(name= "postinumero", nullable = false)
    private Postinumero postinumero;

    @NotBlank(message = "Käyttäjätunnus on pakollinen")
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Salasana on pakollinen")
    @Size(min = 4)
    @Column(name = "password", nullable = false)
    private String passwordHash;

    @NotBlank(message = "Rooli on pakollinen")
    @Size(min = 1, max = 15)
    @Pattern(regexp = "^(USER|ADMIN)$", message = "Rooli on virheellinen")
    @Column(name= "rooli", nullable = false)
    private String rooli;

    @JsonIgnoreProperties("kayttaja")
    @OneToMany(mappedBy= "kayttaja", cascade=CascadeType.ALL)
    private List<Myynti> myynnit;

    public Kayttaja() {
    }

    public Kayttaja(String etunimi, String sukunimi, String katuosoite, LocalDate syntymaaika, String sahkoposti,
            String puhelinnro, String lisatieto, Postinumero postinumero, String username, String passwordHash, String rooli) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.katuosoite = katuosoite;
        this.syntymaaika = syntymaaika;
        this.sahkoposti = sahkoposti;
        this.puhelinnro = puhelinnro;
        this.lisatieto = lisatieto;
        this.postinumero = postinumero;
        this.username = username;
		this.passwordHash = passwordHash;
        this.rooli =rooli;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public LocalDate getSyntymaaika() {
        return syntymaaika;
    }

    public void setSyntymaaika(LocalDate syntymaaika) {
        this.syntymaaika = syntymaaika;
    }

    public Postinumero getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(Postinumero postinumero) {
        this.postinumero = postinumero;
    }

    public String getRooli() {
        return rooli;
    }

    public void setRooli(String rooli) {
        this.rooli = rooli;
    }

    public List<Myynti> getMyynnit() {
        return myynnit;
    }

    public void setMyynnit(List<Myynti> myynnit) {
        this.myynnit = myynnit;
    }

    @Override
    public String toString() {
        return "kayttaja [kayttaja_id=" + kayttaja_id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", katuosoite="
                + katuosoite + ", syntymaaika=" + syntymaaika + ", sahkoposti=" + sahkoposti + ", puhelinnro="
                + puhelinnro + ", lisatieto=" + lisatieto + ", postinumero=" + postinumero + ", rooli=" + rooli + "]";
    }
 
}
