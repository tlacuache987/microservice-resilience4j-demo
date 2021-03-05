package org.certificatic.resilience.helloservice.webclient;

import java.util.Random;

import org.certificatic.resilience.helloservice.webclient.model.Saludo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaludoServiceClient {

	private final WebClient webClient;
	private final Random random;

	public Mono<Saludo> llamarSaludoService() {
		return webClient.get()
				.uri("/saludo/{id}", random())
				.retrieve()
				.bodyToMono(Saludo.class)

		;
	}

	private String random() {
		return String.valueOf((random.nextInt(10) % 5) + 1);
	}
}
