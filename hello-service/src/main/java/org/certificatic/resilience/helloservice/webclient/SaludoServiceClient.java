package org.certificatic.resilience.helloservice.webclient;

import java.util.Random;

import org.certificatic.resilience.helloservice.webclient.model.Saludo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaludoServiceClient {

	private static final String SALUDO_SERVICE_CIRCUIT_BREAKER = "saludoServiceCircuitBreaker";

	private final WebClient webClient;
	private final Random random;

	@CircuitBreaker(name = SALUDO_SERVICE_CIRCUIT_BREAKER, fallbackMethod = "llamarSaludoServiceFallback")
	public Mono<Saludo> llamarSaludoService() {
		return webClient.get()
				.uri("/saludo/{id}", random())
				.retrieve()
				.bodyToMono(Saludo.class)

		;
	}

	public Mono<Saludo> llamarSaludoServiceFallback(Exception ex) {
		log.info("ocurrio un error al llamar al servicio =(");
		return Mono.just(Saludo.builder().saludo("... Hola =(").build());
	}

	private String random() {
		return String.valueOf((random.nextInt(10) % 5) + 1);
	}
}
