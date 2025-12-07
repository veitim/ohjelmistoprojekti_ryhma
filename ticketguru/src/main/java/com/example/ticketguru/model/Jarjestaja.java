package com.example.ticketguru.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;




@Entity
@Table(name = "jarjestaja")
public class Jarjestaja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jarjestaja_id;

    @NotBlank(message = "Järjestäjän nimi on pakollinen")
    @Size(max = 50, message = "Nimi saa olla enintään 50 merkkiä")
    @Column(name = "nimi", nullable = false, length = 50)
    private String nimi;

    @Size(max = 50, message = "Yhteyshenkilö saa olla enintään 50 merkkiä")
    @Column(name = "yhteyshenkilo", length = 50, nullable = false)
    private String yhteyshenkilo;

    @NotBlank(message = "Sähköpostiosoite on pakollinen")
    @Email(message = "Sähköpostiosoite ei ole kelvollinen")
    @Size(max = 100, message = "Sähköposti saa olla enintään 100 merkkiä" )
    @Column(name = "sahkoposti", nullable = false, length = 100)
    private String sahkoposti;

    @Pattern(regexp = "^[0-9+\\-()\\s]*$", message = "Puhelinnumero sisältää virheellisiä merkkejä. Merkit voivat olla: 0-9, + ja -")
    @Size(max = 15, message = "Puhelinnumero saa olla enintään 15 merkkiä")
    @Column(name = "puhelin", length = 15)
    private String puhelin;
    
    @JsonIgnoreProperties({"lipputyyppi", "jarjestaja"})
    @OneToMany(mappedBy = "jarjestaja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
