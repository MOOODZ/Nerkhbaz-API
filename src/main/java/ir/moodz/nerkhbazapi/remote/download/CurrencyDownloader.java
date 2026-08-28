package ir.moodz.nerkhbazapi.remote.download;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.domain.CurrencySymbol;

import java.util.List;
import java.util.Map;

public interface CurrencyDownloader {
    List<Currency> fetch(String url, Map<CurrencySymbol, String> suffix);
}
