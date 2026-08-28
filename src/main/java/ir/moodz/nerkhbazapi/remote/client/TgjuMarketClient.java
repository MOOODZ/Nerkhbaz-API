package ir.moodz.nerkhbazapi.remote.client;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.domain.CurrencySymbol;
import ir.moodz.nerkhbazapi.remote.download.CurrencyDownloader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("tgjuClient")
public class TgjuMarketClient implements MarketClient {

    private final CurrencyDownloader downloader;

    public TgjuMarketClient(CurrencyDownloader downloader) {
        this.downloader = downloader;
    }

    private static final Map<CurrencySymbol, String> SUFFIXES = Map.ofEntries(
            // Currencies
            Map.entry(CurrencySymbol.USD, "us"),
            Map.entry(CurrencySymbol.EUR, "eu"),
            Map.entry(CurrencySymbol.GBP, "uk"),
            Map.entry(CurrencySymbol.CHF, "ch"),
            Map.entry(CurrencySymbol.CAD, "ca"),
            Map.entry(CurrencySymbol.AUD, "au"),
            Map.entry(CurrencySymbol.SEK, "se"),
            Map.entry(CurrencySymbol.NOK, "no"),
            Map.entry(CurrencySymbol.DKK, "dk"),
            Map.entry(CurrencySymbol.INR, "in"),
            Map.entry(CurrencySymbol.AED, "ae"),
            Map.entry(CurrencySymbol.PKR, "pk"),
            Map.entry(CurrencySymbol.IQD, "iq"),
            Map.entry(CurrencySymbol.SYP, "sy"),
            Map.entry(CurrencySymbol.AFN, "af"),
            Map.entry(CurrencySymbol.ILS, "il"),
            Map.entry(CurrencySymbol.AZN, "az"),
            Map.entry(CurrencySymbol.AMD, "am"),
            Map.entry(CurrencySymbol.TRY, "tr"),
            Map.entry(CurrencySymbol.RUB, "ru"),
            Map.entry(CurrencySymbol.THB, "th"),
            Map.entry(CurrencySymbol.HKD, "hk"),
            Map.entry(CurrencySymbol.SAR, "sa"),
            Map.entry(CurrencySymbol.OMR, "om"),
            Map.entry(CurrencySymbol.QAR, "qa"),
            Map.entry(CurrencySymbol.KWD, "kw"),
            Map.entry(CurrencySymbol.BHD, "bh"),
            Map.entry(CurrencySymbol.MYR, "my"),
            Map.entry(CurrencySymbol.CNY, "cn"),
            Map.entry(CurrencySymbol.JPY, "jp"),
            Map.entry(CurrencySymbol.SGD, "sg"),
            Map.entry(CurrencySymbol.NZD, "nz"),
            Map.entry(CurrencySymbol.TMT, "tm"),

            // Coins
            Map.entry(CurrencySymbol.EMAMI_COIN, "sekee"),
            Map.entry(CurrencySymbol.BAHAR_AZADI_COIN, "sekeb"),
            Map.entry(CurrencySymbol.HALF_COIN, "nim"),
            Map.entry(CurrencySymbol.QUARTER_COIN, "rob"),
            Map.entry(CurrencySymbol.ONE_GRAM_COIN, "gerami"),

            // Gold
            Map.entry(CurrencySymbol.GOLD_18K, "geram18"),
            Map.entry(CurrencySymbol.GOLD_24K, "geram24"),
            Map.entry(CurrencySymbol.SECOND_HAND_GOLD, "gold_mini_size"),

            // Silver
            Map.entry(CurrencySymbol.SILVER_999, "silver_999")
    );

    @Override
    public List<Currency> fetchCurrencies() {
        final String currencyUrl = "https://www.tgju.org/currency";
        final String metalUrl = "https://www.tgju.org";
        var currencies = new ArrayList<Currency>();

        currencies.addAll(downloader.fetch(currencyUrl, SUFFIXES));
        currencies.addAll(downloader.fetch(metalUrl, SUFFIXES));

        return currencies;
    }
}
