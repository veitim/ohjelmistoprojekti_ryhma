package com.example.ticketguru.Dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class MyyntiDto {

    @NotNull
    private long kayttaja_id;

    @NotNull
    private List<MyyntiRiviDto> rivit;

    @NotNull
    private String maksutapa;

    public long getKayttaja_id() {
        return kayttaja_id;
    }

    public void setKayttaja_id(long kayttaja_id) {
        this.kayttaja_id = kayttaja_id;
    }

    public String getMaksutapa() {
        return maksutapa;
    }

    public void setMaksutapa(String maksutapa) {
        this.maksutapa = maksutapa;
    }

    public List<MyyntiRiviDto> getRivit() {
        return rivit;
    }

    public void setRivit(List<MyyntiRiviDto> rivit) {
        this.rivit = rivit;
    }

}

