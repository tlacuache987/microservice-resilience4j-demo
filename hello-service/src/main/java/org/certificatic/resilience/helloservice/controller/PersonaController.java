package org.certificatic.resilience.helloservice.controller;

import org.certificatic.resilience.helloservice.document.Persona;
import org.certificatic.resilience.helloservice.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persona")
public class PersonaController {

	private final PersonaService personaService;

	@GetMapping("/saludo/{id}")
	public Mono<ResponseEntity<Persona>> saludo(@PathVariable String id) {

		return personaService.obtenerPersona(id)
				.map(persona -> ResponseEntity.status(HttpStatus.OK).body(persona));
	}
}
