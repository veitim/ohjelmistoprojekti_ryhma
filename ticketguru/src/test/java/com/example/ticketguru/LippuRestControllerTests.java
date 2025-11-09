package com.example.ticketguru;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.ticketguru.Controller.LippuRestController;
import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;

class LippuRestControllerTests {

    @Mock
    private LippuRepository lippuRepository;

    @InjectMocks
    private LippuRestController lippuRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Testi 1: getLippuById palauttaa löytyneen lipun
    @Test
    void testGetLippuById_Found() {
        Lippu lippu = new Lippu();
        lippu.setLippu_id(1L);
        when(lippuRepository.findById(1L)).thenReturn(Optional.of(lippu));

        ResponseEntity<Lippu> response = lippuRestController.getLippuById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lippu, response.getBody());
        verify(lippuRepository, times(1)).findById(1L);
    }

    // ❌ Testi 2: getLippuById palauttaa 404 jos ei löydy
    @Test
    void testGetLippuById_NotFound() {
        when(lippuRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Lippu> response = lippuRestController.getLippuById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // ✅ Testi 3: getLippuByKoodi palauttaa lipun jos koodi löytyy
    @Test
    void testGetLippuByKoodi_Found() {
        Lippu lippu = new Lippu();
        lippu.setKoodi("ABC123");

        when(lippuRepository.findByKoodi("ABC123")).thenReturn(Optional.of(lippu));

        ResponseEntity<?> response = lippuRestController.getLippuByKoodi("ABC123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lippu, response.getBody());
        verify(lippuRepository, times(1)).findByKoodi("ABC123");
    }

    // ❌ Testi 4: getLippuByKoodi palauttaa kaikki liput jos parametri puuttuu
    @Test
    void testGetLippuByKoodi_All() {
        when(lippuRepository.findAll()).thenReturn(List.of(new Lippu(), new Lippu()));

        ResponseEntity<?> response = lippuRestController.getLippuByKoodi(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        verify(lippuRepository, times(1)).findAll();
    }

    // ✅ Testi 5: createLippu tallentaa lipun
    @Test
    void testCreateLippu() {
        Lippu uusi = new Lippu();
        uusi.setPaikka("A1");
        when(lippuRepository.save(uusi)).thenReturn(uusi);

        ResponseEntity<Lippu> response = lippuRestController.createLippu(uusi);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(uusi, response.getBody());
        verify(lippuRepository, times(1)).save(uusi);
    }

    // ✅ Testi 6: deleteLippu poistaa jos löytyy
    @Test
    void testDeleteLippu_Found() {
        Lippu lippu = new Lippu();
        lippu.setLippu_id(3L);
        when(lippuRepository.findById(3L)).thenReturn(Optional.of(lippu));

        ResponseEntity<Void> response = lippuRestController.deleteLippu(3L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(lippuRepository, times(1)).delete(lippu);
    }

    // ❌ Testi 7: deleteLippu palauttaa 404 jos ei löydy
    @Test
    void testDeleteLippu_NotFound() {
        when(lippuRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = lippuRestController.deleteLippu(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lippuRepository, never()).delete(any());
    }

    // ✅ Testi 8: patchLippu päivittää kaytetty-arvon
    @Test
    void testPatchLippu() {
        Lippu lippu = new Lippu();
        lippu.setKaytetty(false);

        Lippu patched = new Lippu();
        patched.setKaytetty(true);

        when(lippuRepository.findById(1L)).thenReturn(Optional.of(lippu));
        when(lippuRepository.save(any(Lippu.class))).thenReturn(lippu);

        ResponseEntity<Lippu> response = lippuRestController.patchLippu(1L, patched);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isKaytetty());
    }
}
