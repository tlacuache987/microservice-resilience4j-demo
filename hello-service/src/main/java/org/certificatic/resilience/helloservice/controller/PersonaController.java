package org.certificatic.resilience.helloservice.controller;

import org.certificatic.resilience.helloservice.document.Persona;
import org.certificatic.resilience.helloservice.service.PersonaService;
import org.certificatic.resilience.helloservice.webclient.SaludoServiceClient;
import org.certificatic.resilience.helloservice.webclient.model.Saludo;
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

	private final SaludoServiceClient saludoServiceClient;

	@GetMapping("/{id}")
	public Mono<ResponseEntity<String>> getPersona(@PathVariable String id) {

		Mono<Persona> personaMono = personaService.obtenerPersona(id);
		Mono<Saludo> saludoMono = saludoServiceClient.llamarSaludoService();

		return Mono.zip(personaMono, saludoMono)
				.flatMap(
						tuple2 -> Mono.just(
								tuple2.getT2().getSaludo().concat(" ")
										.concat(tuple2.getT1().getNombre())))
				.map(saludoString -> ResponseEntity.status(HttpStatus.OK).body(saludoString));
	}
}
