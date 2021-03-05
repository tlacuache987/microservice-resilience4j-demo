package org.certificatic.resilience.saludoservice.service;

import org.certificatic.resilience.saludoservice.documents.Saludo;
import org.certificatic.resilience.saludoservice.repository.SaludoRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaludoService {

	private final SaludoRepository saludoRepository;

	public Saludo obtenerSaludo(String id) {
		return saludoRepository.findById(id).get();
	}
}
