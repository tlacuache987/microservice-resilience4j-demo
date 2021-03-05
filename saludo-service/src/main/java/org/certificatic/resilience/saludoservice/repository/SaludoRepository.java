package org.certificatic.resilience.saludoservice.repository;

import org.certificatic.resilience.saludoservice.documents.Saludo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaludoRepository extends MongoRepository<Saludo, String> {

}
