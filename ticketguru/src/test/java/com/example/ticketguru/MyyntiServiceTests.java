package com.example.ticketguru;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.ticketguru.Service.MyyntiService;
import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.Myyntirivi;

import jakarta.persistence.EntityNotFoundException;

public class MyyntiServiceTests {

    @Mock
    private KayttajaRepository kayttajaRepository;

    @Mock
    private LippuRepository lippuRepository;

    @Mock
    private MyyntiRepository myyntiRepository;

    @InjectMocks
    private MyyntiService myyntiService;
    private Kayttaja kayttaja;
    private Lippu lippu;
    private Myynti myynti;
    private Myyntirivi myyntirivi;

    @BeforeEach
    void alustus() {
        MockitoAnnotations.openMocks(this);

        kayttaja = new Kayttaja();
        kayttaja.setKayttaja_id(1L);

        lippu = new Lippu();
        lippu.setLippu_id(10L);
        lippu.setTila(false);

        myynti = new Myynti();
        myynti.setKayttaja(kayttaja);

        myyntirivi = new Myyntirivi();
        myyntirivi.setLippu(lippu);
        myynti.setMyyntirivit(List.of(myyntirivi));
    }

    @Test
    void luoMyynti() {
        when(kayttajaRepository.existsById(1L)).thenReturn(true);
        when(lippuRepository.findById(10L)).thenReturn(Optional.of(lippu));
        when(myyntiRepository.save(any(Myynti.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Myynti tulos = myyntiService.luoMyynti(myynti);

        assertNotNull(tulos);
        assertEquals(LocalDate.now(), tulos.getPaivamaara());
        assertTrue(lippu.isTila(), "Tila = true");

        verify(lippuRepository).save(lippu);
        verify(myyntiRepository).save(myynti);
    }

    @Test
    void eiKayttajaa() {
        myynti.setKayttaja(null);

        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, () -> {
            myyntiService.luoMyynti(myynti);
        });

        assertEquals("Käyttäjää ei löytynyt annetulla ID:llä", e.getMessage());
    }

    @Test
    void LippuJoMyyty() {
        when(kayttajaRepository.existsById(1L)).thenReturn(true);
        lippu.setTila(true);
        when(lippuRepository.findById(10L)).thenReturn(Optional.of(lippu));

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            myyntiService.luoMyynti(myynti);
        });

        assertEquals("Lippu on jo myyty!", e.getMessage());
        verify(lippuRepository, never()).save(any(Lippu.class));
    }
}

