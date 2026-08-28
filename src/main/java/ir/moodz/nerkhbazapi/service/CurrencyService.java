package ir.moodz.nerkhbazapi.service;

import ir.moodz.nerkhbazapi.database.mapper.CurrencyMapper;
import ir.moodz.nerkhbazapi.database.model.HistoryCollection;
import ir.moodz.nerkhbazapi.database.model.LiveCollection;
import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.remote.client.MarketClient;
import ir.moodz.nerkhbazapi.repository.HistoryRepository;
import ir.moodz.nerkhbazapi.repository.LiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    private final MarketClient marketClient;
    private final LiveRepository liveRepository;
    private final HistoryRepository historyRepository;
    private final CurrencyMapper mapper;

    public CurrencyService(
            @Qualifier("navasanClient") MarketClient marketClient,
            LiveRepository liveRepository,
            HistoryRepository historyRepository,
            CurrencyMapper mapper
    ) {
        this.marketClient = marketClient;
        this.liveRepository = liveRepository;
        this.historyRepository = historyRepository;
        this.mapper = mapper;
    }

    @Scheduled(
            cron = "0 0 11-23/3 * * SAT,SUN,MON,TUE,WED,THU",
            zone = "Asia/Tehran"
    )
    @CachePut(value = "liveCurrencies")
    public List<Currency> fetchCurrencies() {
        List<Currency> currencies =  marketClient.fetchCurrencies();
        List<LiveCollection> liveCollectionCurrencies = mapper.asLiveEntities(currencies);
        liveRepository.saveAll(liveCollectionCurrencies);
        return currencies;
    }

    @Scheduled(
            cron = "0 55 23 * * *",
            zone = "Asia/Tehran"
    )
    public void fetchHistories() {
        List<LiveCollection> latestCurrencies = liveRepository.findAll();
        List<HistoryCollection> historyEntities = mapper.asHistoryEntities(latestCurrencies);
        historyRepository.saveAll(historyEntities);
    }

    @Cacheable("liveCurrencies")
    public List<Currency> getCurrencies() {
        return mapper.asDomainFromLive(liveRepository.findAll());
    }

    public List<Currency> get30DaysHistoryBySymbol(String symbol) {
        return mapper.asDomainFromHistory(historyRepository.findTop30BySymbolOrderByCreateAtDesc(symbol));
    }
}
