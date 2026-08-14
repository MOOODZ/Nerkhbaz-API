package ir.moodz.nerkhbazapi.mapper;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.model.HistoryEntity;
import ir.moodz.nerkhbazapi.model.LiveEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class CurrencyMapper {

    public List<Currency> asDomainFromLive(List<LiveEntity> dtos) {
        return dtos.stream()
                .map(dto ->
                        new Currency(dto.getSymbol(), dto.getPrice(), dto.getCreatedAt())
                ).toList();
    }

    public List<Currency> asDomainFromHistory(List<HistoryEntity> dtos) {
        return dtos.stream()
                .map(dto ->
                        new Currency(dto.getSymbol(), dto.getPrice(), dto.getCreatedAt())
                ).toList();
    }

    public List<HistoryEntity> asHistoryEntities(List<LiveEntity> historyEntities) {
        Instant instant = Instant.now();
        return historyEntities.stream()
                .map(dto ->
                        new HistoryEntity(dto.getSymbol(), dto.getPrice(), instant)
                ).toList();
    }

    public List<LiveEntity> asLiveEntities(List<Currency> currencies) {
        Instant instant = Instant.now();
        return currencies.stream()
                .map(currency ->
                        new LiveEntity(currency.symbol(), currency.price(), instant)
                ).toList();
    }
}
