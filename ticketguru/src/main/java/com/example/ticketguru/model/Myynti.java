package com.example.ticketguru.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "myynti")
public class Myynti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myyntiId;

    @ManyToOne
    @JoinColumn(name = "asiakasId", nullable = false)
    private Asiakas asiakas;

    @Column(nullable = false)
    private LocalDate paivamaara;

    @Column(length = 50)
    private String maksutapa;

    @Column(length = 50)
    private String tyyppi;

    @OneToMany(mappedBy = "myynti", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Myyntirivi> myyntirivit;

    public Myynti() {}

    public Long getMyyntiId() {return myyntiId;}
    public void setMyyntiId(Long myyntiId) {this.myyntiId = myyntiId;}

    public Asiakas getAsiakas() {return asiakas;}
    public void setAsiakas(Asiakas asiakas ) {this.asiakas = asiakas;}

    public LocalDate getPaivamaara() {return paivamaara;}
    public void setPaivamaara(LocalDate paivamaara ) {this.paivamaara = paivamaara;}

    public String getMaksutapa() {return maksutapa;}
    public void setMaksutapa(String maksutapa ) {this.maksutapa = maksutapa;}
    
    public String getTyyppi() {return tyyppi;}
    public void setTyyppi(String tyyppi) {this.tyyppi = tyyppi;}

    public List<Myyntirivi>getMyyntirivit() {return myyntirivit;}
    public void setMyyntirivit(List<Myyntirivi> myyntirivit) {this.myyntirivit = myyntirivit;}
}
