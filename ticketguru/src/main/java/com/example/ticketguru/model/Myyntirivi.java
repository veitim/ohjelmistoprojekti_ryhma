package com.example.ticketguru.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "myyntirivi")
public class Myyntirivi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riviId;
    
    // @NotNull(message = "Myynti ei voi olla tyhjä")
    @JsonIgnoreProperties("myyntirivit")
    @ManyToOne
    @JoinColumn(name = "myynti_id", nullable = false)
    private Myynti myynti;
    
    @NotNull(message = "Lippu ei voi olla tyhjä")
    @JsonIgnoreProperties("myyntirivit")
    @ManyToOne
    @JoinColumn(name = "lippu_id", nullable = false)
    private Lippu lippu;

    
    public Myyntirivi() {}

    public Myyntirivi(Myynti myynti, Lippu lippu, LocalDate paivamaara, BigDecimal summa) {
        this.myynti = myynti;
        this.lippu = lippu;
    }

    public Long getRiviId() { return riviId; }
    public void setRiviId(Long riviId) { this.riviId = riviId; }

    public Myynti getMyynti() { return myynti; }
    public void setMyynti(Myynti myynti) { this.myynti = myynti; }

    public Lippu getLippu() { return lippu; }
    public void setLippu(Lippu lippu) { this.lippu = lippu; }

    @Override
    public String toString() {
        return "Myyntirivi [riviId=" + riviId + ", myynti=" +this.getMyynti() + ", lippu=" + this.getLippu() + "]";
    }

}
