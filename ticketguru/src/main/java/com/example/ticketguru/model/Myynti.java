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
import jakarta.persistence.Table;

@Entity
@Table(name = "myynti")
public class Myynti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myyntiId;

    @JsonIgnoreProperties("myynnit")
    @ManyToOne
    @JoinColumn(name = "kayttajaId", nullable = false)
    private Kayttaja kayttaja;

    @Column(nullable = false)
    private LocalDate paivamaara;

    @Column(length = 50)
    private String maksutapa;

    @Column(length = 50)
    private String tyyppi;

    @JsonIgnoreProperties("myynti")
    @OneToMany(mappedBy = "myynti", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Myyntirivi> myyntirivit;

    public Myynti() {}

    public Myynti(Kayttaja kayttaja, LocalDate paivamaara, String maksutapa, String tyyppi) {
        this.kayttaja = kayttaja;
        this.paivamaara = paivamaara;
        this.maksutapa = maksutapa;
        this.tyyppi = tyyppi;
    }

    public Long getMyyntiId() {return myyntiId;}
    public void setMyyntiId(Long myyntiId) {this.myyntiId = myyntiId;}

    public Kayttaja getKayttaja() {return kayttaja;}
    public void setKayttaja(Kayttaja kayttaja ) {this.kayttaja = kayttaja;}

    public LocalDate getPaivamaara() {return paivamaara;}
    public void setPaivamaara(LocalDate paivamaara ) {this.paivamaara = paivamaara;}

    public String getMaksutapa() {return maksutapa;}
    public void setMaksutapa(String maksutapa ) {this.maksutapa = maksutapa;}
    
    public String getTyyppi() {return tyyppi;}
    public void setTyyppi(String tyyppi) {this.tyyppi = tyyppi;}

    public List<Myyntirivi>getMyyntirivit() {return myyntirivit;}
    public void setMyyntirivit(List<Myyntirivi> myyntirivit) {this.myyntirivit = myyntirivit;}

    @Override
    public String toString() {
        return "Myynti [myyntiId=" + myyntiId + ", kayttaja=" + kayttaja + ", paivamaara=" + paivamaara + ", maksutapa="
                + maksutapa + ", tyyppi=" + tyyppi + ", myyntirivit=" + myyntirivit + "]";
    }

    
}
