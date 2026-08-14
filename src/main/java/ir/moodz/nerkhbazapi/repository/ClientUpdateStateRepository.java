package ir.moodz.nerkhbazapi.repository;

import ir.moodz.nerkhbazapi.model.ClientUpdateState;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientUpdateStateRepository extends MongoRepository<ClientUpdateState, Integer> {}
