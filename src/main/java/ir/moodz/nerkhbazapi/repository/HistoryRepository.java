package ir.moodz.nerkhbazapi.repository;

import ir.moodz.nerkhbazapi.database.model.HistoryCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HistoryRepository extends MongoRepository<HistoryCollection,String> {
    List<HistoryCollection> findTop30BySymbolOrderByCreateAtDesc(String symbol);
}
