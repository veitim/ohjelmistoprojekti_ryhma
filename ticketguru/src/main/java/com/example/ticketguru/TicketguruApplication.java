package com.example.ticketguru;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ticketguru.model.Jarjestaja;
import com.example.ticketguru.model.JarjestajaRepository;
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
import com.example.ticketguru.model.Postinumero;
import com.example.ticketguru.model.PostinumeroRepository;
import com.example.ticketguru.model.Tapahtuma;
import com.example.ticketguru.model.TapahtumaRepository;

@SpringBootApplication
public class TicketguruApplication {
	private static final Logger log = LoggerFactory.getLogger(TicketguruApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketguruApplication.class, args);
	}

	@Bean
	public CommandLineRunner ticketguru(TapahtumaRepository tRepository, JarjestajaRepository jRepository,
			LippuTyyppiRepository ltRepository, PostinumeroRepository pRepository, LippuRepository lippuRepository, MyyntiriviRepository mrRepository, MyyntiRepository mRepository, KayttajaRepository kRepository) {
		return (args) -> {

			log.info("Järjestäjiä");
			Jarjestaja jarjestaja1 = new Jarjestaja("uber", "pekka", "uber@uber", "+3580000");
			Jarjestaja jarjestaja2 = new Jarjestaja("ketkutOy", "Rosmo", "rosmo@rosvo", "+3580001");

			jRepository.save(jarjestaja1);
			jRepository.save(jarjestaja2);

			log.info("Järkkääjät");
			for (Jarjestaja jarjestaja : jRepository.findAll()) {
				log.info(jarjestaja.toString());
			}

			log.info("tapahtumaa");
			tRepository.save(new Tapahtuma("hälläväliä", "koti", LocalDate.of(2025, 1, 7), LocalDate.of(2025, 1, 8),
					"ikärajaa ei ole", jarjestaja1));
			tRepository.save(new Tapahtuma("syljeskellään", "tori", LocalDate.of(2028, 3, 7), LocalDate.of(2028, 4, 10),
					"ikäraja: 67", jarjestaja2));

			log.info("Kaikki tapahtumat");
			for (Tapahtuma tapahtuma : tRepository.findAll()) {
				log.info(tapahtuma.toString());
			}

			log.info("Lipputyyppei");
			ltRepository.save(new LippuTyyppi("Normaali", 50));
			ltRepository.save(new LippuTyyppi("Vanha", 500));

			log.info("Lipputyyppei");
			for (LippuTyyppi lippuTyyppi : ltRepository.findAll()) {
				log.info(lippuTyyppi.toString());
			}

			log.info("Postitoimipaikkoi");
			pRepository.save(new Postinumero("00980", "Helsinki"));
			pRepository.save(new Postinumero("00990", "Helsinki"));

			Tapahtuma tapahtuma = tRepository.findAll().iterator().next();
			LippuTyyppi lipputyyppi = ltRepository.findAll().iterator().next();

			
			Lippu lippu1 = new Lippu("Espoo", true, tapahtuma);
			lippu1.setLipputyyppi(lipputyyppi);
			Lippu lippu2 = new Lippu("Helsinki", false, tapahtuma);
			lippu2.setLipputyyppi(lipputyyppi);

			// Tallennetaan
			lippuRepository.save(lippu1);
			lippuRepository.save(lippu2);

			log.info("Kaikki liput:");
			for (Lippu lippu : lippuRepository.findAll()) {
				log.info(lippu.toString());
			}

			log.info("käyttäjiä");
			Kayttaja kayttaja1 = new Kayttaja("urpo", "max", "manala", "1800", "posti@posti", "0000000", "on oikeesti haudattu", rooli1);

			kRepository.save(kayttaja1);

			log.info("myynti");
			Myynti myynti1 = new Myynti(kayttaja1, LocalDate.of(2025, 10, 1), "käteinen", "turha");
			Myynti myynti2 = new Myynti(kayttaja1, LocalDate.of(2025, 10, 1), "kortti", "hyvä");

			mRepository.save(myynti1);
			mRepository.save(myynti2);

			log.info("myyntirivi");
			Myyntirivi myyntirivi1 = new Myyntirivi(myynti1, lippu1, LocalDate.of(1870, 5, 2), BigDecimal.valueOf(20));
			Myyntirivi myyntirivi2 = new Myyntirivi(myynti2, lippu2, LocalDate.of(1970, 5, 1), BigDecimal.valueOf(100));

			mrRepository.save(myyntirivi1);
			mrRepository.save(myyntirivi2);

		};
	}

}
