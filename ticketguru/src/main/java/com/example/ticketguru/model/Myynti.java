package com.example.ticketguru.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "myynti")
public class Myynti {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myyntiId;
    
    @NotNull(message = "Käyttäjä ei voi olla tyhjä")
    @JsonIgnoreProperties("myynnit")
    @ManyToOne
    @JoinColumn(name = "kayttaja_id", nullable = false)
    private Kayttaja kayttaja;

    @NotNull(message = "Päivämäärä ei voi olla tyhjä")
    @Column(nullable = false)
    private LocalDate paivamaara;
    
    @NotBlank(message = "Maksutapa ei voi olla tyhjä")
    @Size(min = 2, max = 50, message = "Maksutavan pituus 2-50 merkkiä")
    @Column(length = 50)
    private String maksutapa;
    
    @JsonIgnoreProperties("myynti")
    @OneToMany(mappedBy = "myynti", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<Myyntirivi> myyntirivit = new ArrayList<>();
    
    public Myynti() {}
    
    public Myynti(Kayttaja kayttaja, LocalDate paivamaara, String maksutapa, String tyyppi) {
        this.kayttaja = kayttaja;
        this.paivamaara = paivamaara;
        this.maksutapa = maksutapa;
    }
    
    public Long getMyyntiId() {return myyntiId;}
    public void setMyyntiId(Long myyntiId) {this.myyntiId = myyntiId;}
    
    public Kayttaja getKayttaja() {return kayttaja;}
    public void setKayttaja(Kayttaja kayttaja) {this.kayttaja = kayttaja;}
    
    public LocalDate getPaivamaara() {return paivamaara;}
    public void setPaivamaara(LocalDate paivamaara) {this.paivamaara = paivamaara;}
    
    public String getMaksutapa() {return maksutapa;}
    public void setMaksutapa(String maksutapa) {this.maksutapa = maksutapa;}
    
    public List<Myyntirivi> getMyyntirivit() {return myyntirivit;}
    public void setMyyntirivit(List<Myyntirivi> myyntirivit) {this.myyntirivit = myyntirivit;}
    
    @Override
    public String toString() {
        return "Myynti [myyntiId=" + myyntiId + ", kayttaja=" + kayttaja + ", paivamaara=" + paivamaara + ", maksutapa="
                + maksutapa + ", myyntirivit=" + myyntirivit + "]";
    }
}