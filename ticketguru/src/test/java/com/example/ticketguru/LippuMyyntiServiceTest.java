package com.example.ticketguru;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.example.ticketguru.Dto.MyyntiDto;
import com.example.ticketguru.Dto.MyyntiRiviDto;
import com.example.ticketguru.Service.LippuMyyntiService;
import com.example.ticketguru.model.Kayttaja;
import com.example.ticketguru.model.KayttajaRepository;
import com.example.ticketguru.model.Lippu;
import com.example.ticketguru.model.LippuRepository;
import com.example.ticketguru.model.LippuTyyppi;
import com.example.ticketguru.model.LippuTyyppiRepository;
import com.example.ticketguru.model.Myynti;
import com.example.ticketguru.model.MyyntiRepository;
import com.example.ticketguru.model.Myyntirivi;
import com.example.ticketguru.model.MyyntiriviRepository;
import com.example.ticketguru.model.Tapahtuma;

public class LippuMyyntiServiceTest {

    @Mock
    private LippuRepository lippuRepository;
    @Mock
    private LippuTyyppiRepository lipputyyppiRepository;
    @Mock
    private MyyntiRepository myyntiRepository;
    @Mock
    private MyyntiriviRepository myyntiriviRepository;
    @Mock
    private KayttajaRepository kayttajaRepository;

    @InjectMocks
    private LippuMyyntiService lippuMyyntiService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMyynti_createsSaleCorrectly() {

        // ----- Mock data -----
        Kayttaja kayttaja = new Kayttaja();
        kayttaja.setKayttaja_id(1L);

        Tapahtuma tapahtuma = new Tapahtuma();
        tapahtuma.setTapahtumaId(10L);
        tapahtuma.setPaikkamaara(100);
        tapahtuma.setNimi("Rockfest");

        LippuTyyppi tyyppi = new LippuTyyppi();
        tyyppi.setTyyppi_id(5L);
        tyyppi.setHinta(50.0);
        tyyppi.setTapahtuma(tapahtuma);

        when(kayttajaRepository.findById(1L)).thenReturn(Optional.of(kayttaja));
        when(lipputyyppiRepository.findById(5L)).thenReturn(Optional.of(tyyppi));
        when(lippuRepository.countByLipputyyppi_Tapahtuma_TapahtumaId(10L)).thenReturn(0L);

        // MyyntiDto
        MyyntiDto dto = new MyyntiDto();
        dto.setKayttaja_id(1L);
        dto.setMaksutapa("Kortti");

        MyyntiRiviDto r1 = new MyyntiRiviDto();
        r1.setTyyppi_id(5L);
        r1.setMaara(2);

        dto.setRivit(List.of(r1));

        // ----- Execute -----
        Myynti result = lippuMyyntiService.createMyynti(dto);

        // ----- Verify -----
        verify(myyntiRepository, times(1)).save(any(Myynti.class));
        verify(lippuRepository, times(2)).save(any(Lippu.class));
        verify(myyntiriviRepository, times(2)).save(any(Myyntirivi.class));

        assertEquals(100.0, result.getSumma()); // 2 × 50€
        assertEquals(kayttaja, result.getKayttaja());
        assertEquals("Kortti", result.getMaksutapa());
        assertEquals(LocalDate.now(), result.getPaivamaara());
    }

    @Test
    void createMyynti() {

        // ----- Mock data -----
        Kayttaja kayttaja = new Kayttaja();
        kayttaja.setKayttaja_id(1L);

        Tapahtuma tapahtuma = new Tapahtuma();
        tapahtuma.setTapahtumaId(10L);
        tapahtuma.setPaikkamaara(100);
        tapahtuma.setNimi("Rockfest");

        LippuTyyppi tyyppi = new LippuTyyppi();
        tyyppi.setTyyppi_id(5L);
        tyyppi.setHinta(50.0);
        tyyppi.setTapahtuma(tapahtuma);

        when(kayttajaRepository.findById(1L)).thenReturn(Optional.of(kayttaja));
        when(lipputyyppiRepository.findById(5L)).thenReturn(Optional.of(tyyppi));
        when(lippuRepository.countByLipputyyppi_Tapahtuma_TapahtumaId(10L)).thenReturn(0L);

        // MyyntiDto
        MyyntiDto dto = new MyyntiDto();
        dto.setKayttaja_id(1L);
        dto.setMaksutapa("Kortti");

        MyyntiRiviDto r1 = new MyyntiRiviDto();
        r1.setTyyppi_id(5L);
        r1.setMaara(2);

        dto.setRivit(List.of(r1));

        // ----- Execute -----
        Myynti result = lippuMyyntiService.createMyynti(dto);

        // ----- Verify -----
        verify(myyntiRepository, times(1)).save(any(Myynti.class));
        verify(lippuRepository, times(2)).save(any(Lippu.class));
        verify(myyntiriviRepository, times(2)).save(any(Myyntirivi.class));

        assertEquals(100.0, result.getSumma()); // 2 × 50€
        assertEquals(kayttaja, result.getKayttaja());
        assertEquals("Kortti", result.getMaksutapa());
        assertEquals(LocalDate.now(), result.getPaivamaara());
    }

    @Test
    void createMyyntiEiPaikkoja() {

        Kayttaja kayttaja = new Kayttaja();
        kayttaja.setKayttaja_id(1L);

        Tapahtuma tapahtuma = new Tapahtuma();
        tapahtuma.setTapahtumaId(10L);
        tapahtuma.setPaikkamaara(5); // vain 5 paikkaa

        LippuTyyppi tyyppi = new LippuTyyppi();
        tyyppi.setTyyppi_id(5L);
        tyyppi.setHinta(10.0);
        tyyppi.setTapahtuma(tapahtuma);

        when(kayttajaRepository.findById(1L)).thenReturn(Optional.of(kayttaja));
        when(lipputyyppiRepository.findById(5L)).thenReturn(Optional.of(tyyppi));
        when(lippuRepository.countByLipputyyppi_Tapahtuma_TapahtumaId(10L)).thenReturn(4L);

        MyyntiDto dto = new MyyntiDto();
        dto.setKayttaja_id(1L);
        dto.setMaksutapa("Käteinen");
        MyyntiRiviDto r1 = new MyyntiRiviDto();
        r1.setTyyppi_id(5L);
        r1.setMaara(2);

        dto.setRivit(List.of(r1));

        assertThrows(ResponseStatusException.class,
            () -> lippuMyyntiService.createMyynti(dto));
    }

    @Test
    void createMyynti_throwsIfUserNotFound() {

        when(kayttajaRepository.findById(anyLong())).thenReturn(Optional.empty());

        MyyntiDto dto = new MyyntiDto();
        dto.setKayttaja_id(999L);

        assertThrows(RuntimeException.class,
            () -> lippuMyyntiService.createMyynti(dto));
    }
}
