package ir.moodz.nerkhbazapi.repository;

import ir.moodz.nerkhbazapi.database.model.ClientUpdateStateCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientUpdateStateRepository extends MongoRepository<ClientUpdateStateCollection, Integer> {}
