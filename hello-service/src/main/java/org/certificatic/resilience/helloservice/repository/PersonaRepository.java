package org.certificatic.resilience.helloservice.repository;

import org.certificatic.resilience.helloservice.document.Persona;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonaRepository extends ReactiveMongoRepository<Persona, String> {

}
