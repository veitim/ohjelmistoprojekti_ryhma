package com.example.ticketguru.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Asiakas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long asiakas_id;
    
    private String etunimi;
    private String sukunimi;
    private String katuosoite;
    private String sahkoposti;
    private String puhelinnro;
    private String lisatieto;

    public Asiakas() {
    }

    public Asiakas(String etunimi, String sukunimi, String katuosoite, String sahkoposti, String puhelinnro,
            String lisatieto) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.katuosoite = katuosoite;
        this.sahkoposti = sahkoposti;
        this.puhelinnro = puhelinnro;
        this.lisatieto = lisatieto;
    }

    public long getAsiakas_id() {
        return asiakas_id;
    }

    public void setAsiakas_id(long asiakas_id) {
        this.asiakas_id = asiakas_id;
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

    
    
}
