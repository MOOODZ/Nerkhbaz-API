package ir.moodz.nerkhbazapi.repository;

import ir.moodz.nerkhbazapi.model.LiveEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LiveRepository extends MongoRepository<LiveEntity, String> {}
