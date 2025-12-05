package com.example.ticketguru.Dto;

import jakarta.validation.constraints.NotNull;

public class MyyntiRiviDto {

    @NotNull
    private long tyyppi_id;

    @NotNull
    private int maara;

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
}

