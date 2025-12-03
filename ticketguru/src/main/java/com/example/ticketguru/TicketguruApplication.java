package com.example.ticketguru;

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
	public CommandLineRunner ticketguru(
			TapahtumaRepository tRepository, 
			JarjestajaRepository jRepository,
			LippuTyyppiRepository ltRepository, 
			PostinumeroRepository pRepository,
			LippuRepository lippuRepository, 
			MyyntiriviRepository mrRepository, 
			MyyntiRepository mRepository, 
			KayttajaRepository kRepository
		) {
		return (args) -> {

			if (jRepository.count() > 0) {
            return;
        	}

			log.info("Järjestäjiä");
			Jarjestaja jarjestaja1 = new Jarjestaja("uber", "pekka", "uber@uber", "+3580000");
			Jarjestaja jarjestaja2 = new Jarjestaja("ketkutOy", "Rosmo", "rosmo@rosvo", "+3580001");
			jRepository.save(jarjestaja1);
			jRepository.save(jarjestaja2);

			log.info("Postitoimipaikkoi");
			Postinumero postinumero1 = new Postinumero("00720", "PERKELEE");
			Postinumero postinumero2 = new Postinumero("00990", "Helsinki");
			pRepository.save(postinumero1);
			pRepository.save(postinumero2);

			log.info("tapahtumaa");
			Tapahtuma tapahtuma1 = new Tapahtuma(
				"hälläväliä", 
				"koti", 
				LocalDate.of(2025, 1, 7), 
				LocalDate.of(2025, 1, 8),
				"ikärajaa ei ole",
				jarjestaja1, 
				3
			);

			Tapahtuma tapahtuma2 = new Tapahtuma(
				"syljeskellään", 
				"tori", 
				LocalDate.of(2028, 3, 7), 
				LocalDate.of(2028, 4, 10),
				"ikäraja: 67", 
				jarjestaja2, 
				50
			);

			Tapahtuma tapahtuma3 = new Tapahtuma(
				"helvetti", 
				"tori", 
				LocalDate.of(2028, 3, 7), 
				LocalDate.of(2028, 4, 10),
				"ikäraja: 67", 
				jarjestaja2, 
				10
			);
			tRepository.save(tapahtuma1);
			tRepository.save(tapahtuma2);
			tRepository.save(tapahtuma3);

			log.info("Lipputyyppei");
			LippuTyyppi lipputyyppi1 = new LippuTyyppi("Perus", 50, tapahtuma1);
			LippuTyyppi lipputyyppi2 = new LippuTyyppi("Normi", 100, tapahtuma2);
			LippuTyyppi lipputyyppi3 = new LippuTyyppi("1Päivä", 70, tapahtuma3);
			LippuTyyppi lipputyyppi4 = new LippuTyyppi("Vanhus", 80, tapahtuma1);
			LippuTyyppi lipputyyppi5 = new LippuTyyppi("Ovi", 100, tapahtuma2);
			ltRepository.save(lipputyyppi1);
			ltRepository.save(lipputyyppi2);
			ltRepository.save(lipputyyppi3);
			ltRepository.save(lipputyyppi4);
			ltRepository.save(lipputyyppi5);

			Lippu lippu1 = new Lippu("A1", true, lipputyyppi1, "12345a");
			Lippu lippu2 = new Lippu("manala", false, lipputyyppi4, "12345b");
			Lippu lippu3 = new Lippu("lessinki", false, lipputyyppi3, "12345c");
			Lippu lippu4 = new Lippu("A2", false, lipputyyppi5, "12345d");
			Lippu lippu5 = new Lippu("A3", false, lipputyyppi5, "12345e");
			Lippu lippu6 = new Lippu("A4", false, lipputyyppi5, "12345f");
			Lippu lippu7 = new Lippu("A5", false, lipputyyppi5, "12345g");
			lippuRepository.save(lippu1);
			lippuRepository.save(lippu2);
			lippuRepository.save(lippu3);
			lippuRepository.save(lippu4);
			lippuRepository.save(lippu5);
			lippuRepository.save(lippu6);
			lippuRepository.save(lippu7);

			log.info("käyttäjiä");
			Kayttaja kayttaja1 = new Kayttaja(
				"urpo", 
				"max", 
				"manala", 
				LocalDate.of(1990, 9, 1), 
				"posti@posti", 
				"0000000", 
				"on oikeesti haudattu", 
				postinumero1, 
				"user", 
				"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", 
				"USER"
			);
			Kayttaja kayttaja2 = new Kayttaja(
				"timo", 
				"samuel", 
				"manala", 
				LocalDate.of(1990, 9, 1), 
				"posti@posti", 
				"0000000", 
				"on oikeesti haudattu", 
				postinumero2, 
				"admin", 
				"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", 
				"ADMIN"
			);
			kRepository.save(kayttaja1);
			kRepository.save(kayttaja2);

			log.info("myynti");
			Myynti myynti1 = new Myynti(kayttaja1, 
				LocalDate.of(2025, 10, 1),
				"käteinen", 130.0);
			Myynti myynti2 = new Myynti(kayttaja2, 
				LocalDate.of(2025, 10, 2), 
				"kortti", 70.0);
			Myynti myynti3 = new Myynti(kayttaja2, 
				LocalDate.of(2025, 10, 3), 
				"kortti", 400.0);
			mRepository.save(myynti1);
			mRepository.save(myynti2);
			mRepository.save(myynti3);

			log.info("myyntirivi");
			Myyntirivi myyntirivi1 = new Myyntirivi(myynti1, lippu1);
			Myyntirivi myyntirivi2 = new Myyntirivi(myynti1, lippu2);
			Myyntirivi myyntirivi3 = new Myyntirivi(myynti2, lippu3);
			Myyntirivi myyntirivi4 = new Myyntirivi(myynti3, lippu4);
			Myyntirivi myyntirivi5 = new Myyntirivi(myynti3, lippu5);
			Myyntirivi myyntirivi6 = new Myyntirivi(myynti3, lippu6);
			Myyntirivi myyntirivi7 = new Myyntirivi(myynti3, lippu7);
			mrRepository.save(myyntirivi1);
			mrRepository.save(myyntirivi2);
			mrRepository.save(myyntirivi3);
			mrRepository.save(myyntirivi4);
			mrRepository.save(myyntirivi5);
			mrRepository.save(myyntirivi6);
			mrRepository.save(myyntirivi7);

		};
	}

}
