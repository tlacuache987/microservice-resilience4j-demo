package org.certificatic.resilience.saludoservice.controller;

import org.certificatic.resilience.saludoservice.document.Saludo;
import org.certificatic.resilience.saludoservice.service.SaludoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saludo")
public class SaludoController {

	private final SaludoService saludoService;

	@GetMapping("/{id}")
	public Saludo getSaludo(@PathVariable String id) {
		return saludoService.obtenerSaludo(id);
	}
}
