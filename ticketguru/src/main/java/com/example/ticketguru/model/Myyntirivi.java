package com.example.ticketguru.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "myyntirivi")
public class Myyntirivi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riviId;
    
    @ManyToOne
    @JoinColumn(name = "myynti_id", nullable = false)
    private Myynti myynti;
    
    @ManyToOne
    @JoinColumn(name = "lippu_id", nullable = false)
    private Lippu lippu;
    
    @Column(nullable = false)
    private LocalDate paivamaara;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal summa;
    
    public Myyntirivi() {}

    public Myyntirivi(Myynti myynti, Lippu lippu, LocalDate paivamaara, BigDecimal summa) {
        this.myynti = myynti;
        this.lippu = lippu;
        this.paivamaara = paivamaara;
        this.summa = summa;
    }

    public Long getRiviId() { return riviId; }
    public void setRiviId(Long riviId) { this.riviId = riviId; }

    public Myynti getMyynti() { return myynti; }
    public void setMyynti(Myynti myynti) { this.myynti = myynti; }

    public Lippu getLippu() { return lippu; }
    public void setLippu(Lippu lippu) { this.lippu = lippu; }

    public LocalDate getPaivamaara() { return paivamaara; }
    public void setPaivamaara(LocalDate paivamaara) { this.paivamaara = paivamaara; }

    public BigDecimal getSumma() { return summa; }
    public void setSumma(BigDecimal summa) { this.summa = summa; }

    @Override
    public String toString() {
        return "Myyntirivi [riviId=" + riviId + ", myynti=" +this.getMyynti() + ", lippu=" + this.getLippu() + ", paivamaara=" + paivamaara
                + ", summa=" + summa + "]";
    }

}
