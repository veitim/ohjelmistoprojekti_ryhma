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
import com.example.ticketguru.model.Tapahtuma;
import com.example.ticketguru.model.TapahtumaRepository;

@SpringBootApplication
public class TicketguruApplication {
	private static final Logger log = LoggerFactory.getLogger(TicketguruApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketguruApplication.class, args);
	}

	@Bean
		public CommandLineRunner ticketguru(TapahtumaRepository tRepository, JarjestajaRepository jRepository) {
		return (args) -> {

			log.info("Järjestäjiä");
			Jarjestaja jarjestaja1 = new Jarjestaja("uber", "pekka", "uber@uber", "+3580000");
			Jarjestaja jarjestaja2 = new Jarjestaja("ketkutOy", "Rosmo", "rosmo@rosvo", "+3580001");

			jRepository.save(jarjestaja1);
			jRepository.save(jarjestaja2);

			log.info("Pari tapahtumaa");
			tRepository.save(new Tapahtuma("hälläväliä", "koti", LocalDate.of(2025, 1, 7), LocalDate.of(2025, 1, 8), "ikärajaa ei ole", jarjestaja1 ));
			tRepository.save(new Tapahtuma("syljeskellään", "tori", LocalDate.of(2028, 3, 7), LocalDate.of(2028, 4, 10), "ikäraja: 67", jarjestaja2 ));
			
			log.info("Kaikki tapahtumat");
			for (Tapahtuma tapahtuma : tRepository.findAll()) {
				log.info(tapahtuma.toString());
			}
		};
	}

}
