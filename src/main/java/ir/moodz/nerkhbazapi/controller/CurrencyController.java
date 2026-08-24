package ir.moodz.nerkhbazapi.controller;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.mapper.CurrencyMapper;
import ir.moodz.nerkhbazapi.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final Logger log = LoggerFactory.getLogger(CurrencyController.class);
    private final CurrencyService service;
    private final CurrencyMapper mapper;

    public CurrencyController(
            CurrencyService service,
            CurrencyMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public void updateCurrencies() {
        service.fetchCurrencies();
    }

    @GetMapping
    public List<Currency> getLiveCurrencies() {
        return mapper.asDomainFromLive(service.getCurrencies());
    }

    @PostMapping(path = "/{symbol}")
    public List<Currency> getLast30CurrencyHistory(@PathVariable String symbol) {
        return mapper.asDomainFromHistory(service.get30DaysHistoryBySymbol(symbol));
    }

}
