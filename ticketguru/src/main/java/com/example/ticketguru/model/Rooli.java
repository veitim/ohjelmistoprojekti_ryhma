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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
public class Rooli {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long rooli_id;

@NotBlank(message = "Roolin nimi on pakollinen")
@Size(max = 50, message = "Roolin nimi saa olla enintään 50 merkkiä")
@Column(name = "nimi", length = 50, nullable = false)
private String name;

@JsonIgnoreProperties("rooli")
@OneToMany(mappedBy= "rooli", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
private List<Kayttaja> kayttajat;

public Rooli() {
}

public Rooli(String name) {
    this.name = name;
}

public long getRooli_id() {
    return rooli_id;
}

public void setRooli_id(long rooli_id) {
    this.rooli_id = rooli_id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public List<Kayttaja> getKayttajat() {
    return kayttajat;
}

public void setKayttajat(List<Kayttaja> kayttajat) {
    this.kayttajat = kayttajat;
}

@Override
public String toString() {
    return "Rooli [rooli_id=" + rooli_id + ", name=" + name + "]";
}



}
