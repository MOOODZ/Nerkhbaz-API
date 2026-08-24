package ir.moodz.nerkhbazapi.repository;

import ir.moodz.nerkhbazapi.model.collection.HistoryCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HistoryRepository extends MongoRepository<HistoryCollection,String> {
    List<HistoryCollection> findTop30BySymbolOrderByCreateAtDesc(String symbol);
}
