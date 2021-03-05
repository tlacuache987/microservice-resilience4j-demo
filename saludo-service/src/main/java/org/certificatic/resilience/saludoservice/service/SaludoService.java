package org.certificatic.resilience.saludoservice.service;

import org.certificatic.resilience.saludoservice.documents.Saludo;
import org.certificatic.resilience.saludoservice.repository.SaludoRepository;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaludoService {

	private final String CIRCUIT_BREAKER = "saludoRepositoryCircuitBreaker";

	private final SaludoRepository saludoRepository;

	@CircuitBreaker(name = CIRCUIT_BREAKER, fallbackMethod = "obtenerSaludoFallback")
	public Saludo obtenerSaludo(String id) {
		return saludoRepository.findById(id).get();
	}

	public Saludo obtenerSaludoFallback(String id, Exception ex) {
		log.info("ups saludo id {} no encontrado, retornando fallback");
		return Saludo.builder().id(id).saludo("¿Qué onda?").build();
	}
}
