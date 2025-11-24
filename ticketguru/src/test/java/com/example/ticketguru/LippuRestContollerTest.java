package com.example.ticketguru;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.example.ticketguru.Service.LippuService;

class LippuRestControllerTest {

    @Mock
    private LippuRepository lippuRepository;

    @InjectMocks
    private LippuRestController lippuRestController;
    private LippuService lippuService;

    private Lippu lippu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        lippu = new Lippu();
        lippu.setLippu_id(1L);
        lippu.setPaikka("A1");
        lippu.setKaytetty(false);
        lippu.setKoodi("ABC123");
    }

    // ✅ 1: lipun haku ID:llä onnistuu
    @Test
    void testGetLippuById_Found() {
        when(lippuRepository.findById(1L)).thenReturn(Optional.of(lippu));

        ResponseEntity<Lippu> response = lippuRestController.getLippuById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lippu, response.getBody());
        verify(lippuRepository, times(1)).findById(1L);
    }

    // ❌ 2: lipun haku ID:llä epäonnistuu
    @Test
    void testGetLippuById_NotFound() {
        when(lippuRepository.findById(1L)).thenReturn(Optional.of(lippu));

        ResponseEntity<Lippu> response = lippuRestController.getLippuById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lippuRepository, times(1)).findById(99L);
    }

    // ✅ 3: lipun haku koodilla onnistuu
    @Test
    void testGetLippuByKoodi_Found() {
        when(lippuRepository.findByKoodi("ABC123")).thenReturn(Optional.of(lippu));

        ResponseEntity<?> response = lippuRestController.getLippuByKoodi("ABC123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lippu, response.getBody());
        verify(lippuRepository, times(1)).findByKoodi("ABC123");
    }

    // ✅ 4: kaikkien lippujen haku ilman koodia
    @Test
    void testGetAllLiput() {
        when(lippuRepository.findAll()).thenReturn(List.of(lippu));

        ResponseEntity<?> response = lippuRestController.getLippuByKoodi(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(lippu), response.getBody());
        verify(lippuRepository, times(1)).findAll();
    }

    // ✅ 5: lipun luominen
    @Test
    void testCreateLippu() {
        when(lippuRepository.save(any(Lippu.class))).thenReturn(lippu);

        ResponseEntity<?> response = lippuRestController.luoLippu(lippu);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lippu, response.getBody());
        verify(lippuRepository, times(1)).save(lippu);
    }

    // ✅ 6: lipun päivitys onnistuu
    @Test
    void testUpdateLippu_Found() {
        Lippu updated = new Lippu();
        updated.setPaikka("B2");
        updated.setKaytetty(true);
        updated.setLippu_id(1L);

        when(lippuRepository.findById(1L)).thenReturn(Optional.of(lippu));
        when(lippuRepository.save(any(Lippu.class))).thenReturn(updated);

        ResponseEntity<Lippu> response = lippuRestController.updateLippu(1L, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("B2", response.getBody().getPaikka());
        verify(lippuRepository, times(1)).save(any(Lippu.class));
    }

    // ❌ 7: lipun päivitys epäonnistuu, jos ei löydy
    @Test
    void testUpdateLippu_NotFound() {
        when(lippuRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Lippu> response = lippuRestController.updateLippu(2L, lippu);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lippuRepository, never()).save(any());
    }

    // ✅ 8: lipun poistaminen onnistuu
    @Test
    void testDeleteLippu_Found() {
        when(lippuRepository.findById(1L)).thenReturn(Optional.of(lippu));

        ResponseEntity<Void> response = lippuRestController.deleteLippu(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(lippuRepository, times(1)).delete(lippu);
    }

    // ❌ 9: lipun poistaminen epäonnistuu
    @Test
    void testDeleteLippu_NotFound() {
        when(lippuRepository.findById(5L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = lippuRestController.deleteLippu(5L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lippuRepository, never()).delete(any());
    }

    // ✅ 10: lipun patch (päivittää vain kaytetty)
    @Test
    void testPatchLippu() {
        when(lippuRepository.findById(1L)).thenReturn(Optional.of(lippu));
        when(lippuRepository.save(any(Lippu.class))).thenReturn(lippu);

        Lippu patched = new Lippu();
        patched.setKaytetty(true);

        ResponseEntity<Lippu> response = lippuRestController.patchLippu(1L, patched);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isKaytetty());
        verify(lippuRepository).save(any(Lippu.class));
    }

    // ❌ 11: patch epäonnistuu jos lippua ei löydy
    @Test
    void testPatchLippu_NotFound() {
        when(lippuRepository.findById(10L)).thenReturn(Optional.empty());

        Lippu patched = new Lippu();
        patched.setKaytetty(true);

        ResponseEntity<Lippu> response = lippuRestController.patchLippu(10L, patched);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lippuRepository, never()).save(any());
    }
}
