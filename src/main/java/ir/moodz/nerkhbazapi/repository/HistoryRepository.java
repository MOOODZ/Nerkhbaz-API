package ir.moodz.nerkhbazapi.repository;

import ir.moodz.nerkhbazapi.model.HistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HistoryRepository extends MongoRepository<HistoryEntity,String> {
    List<HistoryEntity> findTop30BySymbolOrderByCreatedAtDesc(String symbol);
}
