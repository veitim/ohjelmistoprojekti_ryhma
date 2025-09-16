package com.example.ticketguru.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jarjestaja")
public class Jarjestaja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jarjestaja_id;
    
    @Column(name = "nimi", nullable = false, length = 255)
    private String nimi;
    
    @Column(name = "yhteyshenkilo", length = 255)
    private String yhteyshenkilo;
    
    @Column(name = "sahkoposti", length = 255)
    private String sahkoposti;
    
    @Column(name = "puhelin", length = 20)
    private String puhelin;
    
    @OneToMany(mappedBy = "jarjestajaId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tapahtuma> tapahtumat;
    
    public Jarjestaja() {}
    
    public Jarjestaja(String nimi, String yhteyshenkilo, String sahkoposti, String puhelin) {
        this.nimi = nimi;
        this.yhteyshenkilo = yhteyshenkilo;
        this.sahkoposti = sahkoposti;
        this.puhelin = puhelin;
    }

    public long getJarjestaja_id() {
        return jarjestaja_id;
    }
    
    public void setJarjestaja_id(long jarjestaja_id) {
        this.jarjestaja_id = jarjestaja_id;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public String getYhteyshenkilo() {
        return yhteyshenkilo;
    }
    
    public void setYhteyshenkilo(String yhteyshenkilo) {
        this.yhteyshenkilo = yhteyshenkilo;
    }
    
    public String getSahkoposti() {
        return sahkoposti;
    }
    
    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }
    
    public String getPuhelin() {
        return puhelin;
    }
    
    public void setPuhelin(String puhelin) {
        this.puhelin = puhelin;
    }
    
    public List<Tapahtuma> getTapahtumat() {
        return tapahtumat;
    }
    
    public void setTapahtumat(List<Tapahtuma> tapahtumat) {
        this.tapahtumat = tapahtumat;
    }
    
    @Override
    public String toString() {
        return "Jarjestaja{" +
                "jarjestaja_id=" + jarjestaja_id +
                ", nimi='" + nimi + '\'' +
                ", yhteyshenkilo='" + yhteyshenkilo + '\'' +
                ", sahkoposti='" + sahkoposti + '\'' +
                ", puhelin='" + puhelin + '\'' +
                '}';
    }
}
