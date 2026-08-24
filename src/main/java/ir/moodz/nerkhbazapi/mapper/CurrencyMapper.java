package ir.moodz.nerkhbazapi.mapper;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.model.collection.HistoryCollection;
import ir.moodz.nerkhbazapi.model.collection.LiveCollection;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class CurrencyMapper {

    public List<Currency> asDomainFromLive(List<LiveCollection> entities) {
        return entities.stream()
                .map(dto ->
                        new Currency(dto.getSymbol(), dto.getPrice(), dto.getCreateAt())
                ).toList();
    }

    public List<Currency> asDomainFromHistory(List<HistoryCollection> entities) {
        return entities.stream()
                .map(dto ->
                        new Currency(dto.getSymbol(), dto.getPrice(), dto.getCreateAt())
                ).toList();
    }

    public List<HistoryCollection> asHistoryEntities(List<LiveCollection> historyEntities) {
        Instant instant = Instant.now();
        return historyEntities.stream()
                .map(dto ->
                        new HistoryCollection(dto.getSymbol(), dto.getPrice(), instant)
                ).toList();
    }

    public List<LiveCollection> asLiveEntities(List<Currency> currencies) {
        Instant instant = Instant.now();
        return currencies.stream()
                .map(currency ->
                        new LiveCollection(currency.symbol(), currency.price(), instant)
                ).toList();
    }
}
