package com.example.ticketguru.Dto;

import jakarta.validation.constraints.NotNull;

public class MyyntiDto {

    @NotNull
    private long kayttaja_id;

    @NotNull
    private long tyyppi_id;

    @NotNull
    private int maara;

    @NotNull
    private String maksutapa;

    public long getKayttaja_id() {
        return kayttaja_id;
    }

    public void setKayttaja_id(long kayttaja_id) {
        this.kayttaja_id = kayttaja_id;
    }

    public long getTyyppi_id() {
        return tyyppi_id;
    }

    public void setTyyppi_id(long tyyppi_id) {
        this.tyyppi_id = tyyppi_id;
    }

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }

    public String getMaksutapa() {
        return maksutapa;
    }

    public void setMaksutapa(String maksutapa) {
        this.maksutapa = maksutapa;
    }

}

