package com.example.ticketguru;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ticketguru.Controller.MyyntiRestController;
import com.example.ticketguru.Service.MyyntiService;
import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;



class MyyntiRestControllerTest {

    @Mock
    private MyyntiRepository repository;

    @Mock
    private MyyntiService myyntiService;

    @InjectMocks
    private MyyntiRestController controller;

    private Myynti myynti;

    @BeforeEach
    void alustus() {
        MockitoAnnotations.openMocks(this);
        myynti = new Myynti();
        myynti.setMyyntiId(1L);
        myynti.setKayttaja(new Kayttaja());
        myynti.setPaivamaara(LocalDate.now());
        myynti.setMaksutapa("Kortti");
        myynti.setTyyppi("Online");
        myynti.setMyyntirivit(new ArrayList<>());
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(myynti));

        Iterable<Myynti> tulos = controller.getAll();

        assertNotNull(tulos);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetMyyntiById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(myynti));

        ResponseEntity<Myynti> vastaus = controller.getMyyntiById(1L);

        assertEquals(HttpStatus.OK, vastaus.getStatusCode());
        assertNotNull(vastaus.getBody());
    }

    @Test
    void testGetMyyntiById_NotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Myynti> vastaus = controller.getMyyntiById(99L);

        assertEquals(HttpStatus.NOT_FOUND, vastaus.getStatusCode());
        assertNull(vastaus.getBody());
    }

    @Test
    void testLuoMyynti() {
        when(myyntiService.luoMyynti(any(Myynti.class))).thenReturn(myynti);

        ResponseEntity<?> vastaus = controller.luoMyynti(myynti);

        assertEquals(HttpStatus.OK, vastaus.getStatusCode());
        assertTrue(vastaus.getBody() instanceof Myynti);
        verify(myyntiService, times(1)).luoMyynti(any(Myynti.class));
    }

    @Test
    void testDelete_Found() {
        when(repository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> vastaus = controller.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, vastaus.getStatusCode());
        verify(repository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(repository.existsById(2L)).thenReturn(false);

        ResponseEntity<Void> vastaus = controller.delete(2L);

        assertEquals(HttpStatus.NOT_FOUND, vastaus.getStatusCode());
        verify(repository, never()).deleteById(any());
    }

    @Test
    void testUpdate_Found() {
        Myynti updated = new Myynti();
        updated.setKayttaja(new Kayttaja());
        updated.setPaivamaara(LocalDate.of(2025, 1, 1));
        updated.setMaksutapa("Kortti");
        updated.setTyyppi("Uusi");
        updated.setMyyntirivit(new ArrayList<>());

        when(repository.findById(1L)).thenReturn(Optional.of(myynti));
        when(myyntiService.luoMyynti(any(Myynti.class))).thenReturn(updated);

        ResponseEntity<?> vastaus = controller.update(1L, updated);

        assertEquals(HttpStatus.OK, vastaus.getStatusCode());
        verify(myyntiService, times(1)).luoMyynti(any(Myynti.class));
    }

    @Test
    void testUpdate_NotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<?> vastaus = controller.update(99L, myynti);

        assertEquals(HttpStatus.NOT_FOUND, vastaus.getStatusCode());
        verify(myyntiService, never()).luoMyynti(any());
    }
}
