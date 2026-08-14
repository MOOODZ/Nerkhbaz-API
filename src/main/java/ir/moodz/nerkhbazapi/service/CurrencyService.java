package ir.moodz.nerkhbazapi.service;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.mapper.CurrencyMapper;
import ir.moodz.nerkhbazapi.remote.client.MarketClient;
import ir.moodz.nerkhbazapi.model.HistoryEntity;
import ir.moodz.nerkhbazapi.model.LiveEntity;
import ir.moodz.nerkhbazapi.repository.HistoryRepository;
import ir.moodz.nerkhbazapi.repository.LiveRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CurrencyService {

    private final MarketClient marketClient;
    private final LiveRepository liveRepository;
    private final HistoryRepository historyRepository;
    private final CurrencyMapper mapper;

    public CurrencyService(
            MarketClient marketClient,
            LiveRepository liveRepository,
            HistoryRepository historyRepository,
            CurrencyMapper mapper
    ) {
        this.marketClient = marketClient;
        this.liveRepository = liveRepository;
        this.historyRepository = historyRepository;
        this.mapper = mapper;
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void fetchCurrencies() {
        List<Currency> currencies =  marketClient.fetchCurrencies();
        List<LiveEntity> liveEntityCurrencies = mapper.asLiveEntities(currencies);
        liveRepository.saveAll(liveEntityCurrencies);
    }

    @Scheduled(
            cron = "0 55 23 * * *",
            zone = "Asia/Tehran"
    )
    public void fetchHistories() {
        List<LiveEntity> latestCurrencies = liveRepository.findAll();
        List<HistoryEntity> historyEntities = mapper.asHistoryEntities(latestCurrencies);
        historyRepository.saveAll(historyEntities);
    }

    public List<LiveEntity> getCurrencies() {
        return liveRepository.findAll();
    }

    public List<HistoryEntity> get30DaysHistoryBySymbol(String symbol) {
        return historyRepository.findTop30BySymbolOrderByCreatedAtDesc(symbol);
    }
}
