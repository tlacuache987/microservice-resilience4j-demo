package org.certificatic.resilience.helloservice.service;

import org.certificatic.resilience.helloservice.document.Persona;
import org.certificatic.resilience.helloservice.repository.PersonaRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonaService {

	private final PersonaRepository personaRepository;

	public Mono<Persona> obtenerPersona(String id) {
		return personaRepository.findById(id);
	}
}
