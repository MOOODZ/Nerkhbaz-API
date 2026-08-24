package ir.moodz.nerkhbazapi.repository;

import ir.moodz.nerkhbazapi.model.collection.LiveCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LiveRepository extends MongoRepository<LiveCollection, String> {}
