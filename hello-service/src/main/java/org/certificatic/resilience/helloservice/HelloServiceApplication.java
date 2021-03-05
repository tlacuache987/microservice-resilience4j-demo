package org.certificatic.resilience.helloservice;

import java.util.Random;
import java.util.stream.Stream;

import org.certificatic.resilience.helloservice.document.Persona;
import org.certificatic.resilience.helloservice.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class HelloServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(PersonaRepository personaRepository) {
		return (args) -> {

			Flux.fromStream(Stream.of(new Persona("1", "Paula"),
					new Persona("2", "Paulina"),
					new Persona("3", "Paulette")))
					.flatMap(persona -> personaRepository.save(persona))
					.thenMany(personaRepository.findAll())
					.collectList()
					.subscribe(personaList -> personaList.forEach(p -> System.out.println(p)));
		};
	}

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.baseUrl("http://localhost:9092")
				.build();
	}

	@Bean
	public Random random() {
		return new Random();
	}
}
