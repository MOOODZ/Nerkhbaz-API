package ir.moodz.nerkhbazapi.controller;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @PostMapping
    public void updateCurrencies() {
        service.fetchCurrencies();
    }

    @GetMapping
    public List<Currency> getLiveCurrencies() {
        return service.getCurrencies();
    }

    @PostMapping(path = "/{symbol}")
    public List<Currency> getLast30CurrencyHistory(@PathVariable String symbol) {
        return service.get30DaysHistoryBySymbol(symbol);
    }

}
