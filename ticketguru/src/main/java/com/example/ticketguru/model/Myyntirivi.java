package com.example.ticketguru.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "myyntirivi")
public class Myyntirivi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riviId;
    
    @NotNull(message = "Myynti ei voi olla tyhjä")
    @JsonIgnoreProperties("myyntirivit")
    @ManyToOne
    @JoinColumn(name = "myynti_id", nullable = false)
    private Myynti myynti;
    
    @NotNull(message = "Lippu ei voi olla tyhjä")
    @JsonIgnoreProperties("myyntirivit")
    @ManyToOne
    @JoinColumn(name = "lippu_id", nullable = false)
    private Lippu lippu;
    
    @NotNull(message = "Päivämäärä ei voi olla tyhjä")
    @Column(nullable = false)
    private LocalDate paivamaara;
    
    @NotNull(message = "Summa ei voi olla tyhjä")
    @DecimalMin(value = "0.00", inclusive = false, message = "Summan täytyy olla positiivinen")
    @Digits(integer = 8, fraction = 2, message = "Summa voi olla max 8 numeroa ja 2 desimaalia")
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
