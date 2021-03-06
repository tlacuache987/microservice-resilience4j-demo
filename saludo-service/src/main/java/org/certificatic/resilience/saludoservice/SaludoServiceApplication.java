package org.certificatic.resilience.saludoservice;

import java.util.Arrays;
import java.util.List;

import org.certificatic.resilience.saludoservice.document.Saludo;
import org.certificatic.resilience.saludoservice.repository.SaludoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SaludoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaludoServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUp(SaludoRepository saludoRepository) {
		return (args) -> {
			List<Saludo> saludos = Arrays.asList(
					Saludo.builder().id("1").saludo("Buenos días").build(),
					Saludo.builder().id("2").saludo("Buenas tardes").build(),
					Saludo.builder().id("3").saludo("Buenas madrugadas").build());

			saludoRepository.saveAll(saludos);
		};
	}

}
