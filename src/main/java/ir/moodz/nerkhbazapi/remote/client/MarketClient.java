package ir.moodz.nerkhbazapi.remote.client;

import ir.moodz.nerkhbazapi.domain.Currency;

import java.util.List;

public interface MarketClient {
    List<Currency> fetchCurrencies();
}
