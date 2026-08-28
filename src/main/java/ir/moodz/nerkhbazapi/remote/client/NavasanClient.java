package ir.moodz.nerkhbazapi.remote.client;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.domain.CurrencySymbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component("navasanClient")
public class NavasanClient implements MarketClient{

    private static final String BASE_URL = "http://api.navasan.tech";
    private final Logger log = LoggerFactory.getLogger(NavasanClient.class);
    private final String apiKey;
    private final RestClient restClient;

    public NavasanClient (
            RestClient.Builder builder,
            @Value("${navasan.api-key}") String apiKey
    ){
        this.restClient = builder
                .baseUrl(BASE_URL)
                .build();
        this.apiKey = apiKey;
    }

    private static final Map<String, CurrencyUtil> SYMBOLS = Map.ofEntries(
            // Currencies
            Map.entry("usdt", new CurrencyUtil(CurrencySymbol.USD, false)),
            Map.entry("eur", new CurrencyUtil(CurrencySymbol.EUR, false)),
            Map.entry("gbp", new CurrencyUtil(CurrencySymbol.GBP, false)),
            Map.entry("chf", new CurrencyUtil(CurrencySymbol.CHF, false)),
            Map.entry("cad", new CurrencyUtil(CurrencySymbol.CAD, false)),
            Map.entry("aud", new CurrencyUtil(CurrencySymbol.AUD, false)),
            Map.entry("sek", new CurrencyUtil(CurrencySymbol.SEK, false)),
            Map.entry("nok", new CurrencyUtil(CurrencySymbol.NOK, false)),
            Map.entry("dkk", new CurrencyUtil(CurrencySymbol.DKK, false)),
            Map.entry("inr", new CurrencyUtil(CurrencySymbol.INR, false)),
            Map.entry("aed", new CurrencyUtil(CurrencySymbol.AED, false)),
            Map.entry("pkr", new CurrencyUtil(CurrencySymbol.PKR, false)),
            Map.entry("iqd", new CurrencyUtil(CurrencySymbol.IQD, false)),
            Map.entry("syp", new CurrencyUtil(CurrencySymbol.SYP, false)),
            Map.entry("afn", new CurrencyUtil(CurrencySymbol.AFN, false)),
            Map.entry("azn", new CurrencyUtil(CurrencySymbol.AZN, false)),
            Map.entry("amd", new CurrencyUtil(CurrencySymbol.AMD, false)),
            Map.entry("try", new CurrencyUtil(CurrencySymbol.TRY, false)),
            Map.entry("rub", new CurrencyUtil(CurrencySymbol.RUB, false)),
            Map.entry("thb", new CurrencyUtil(CurrencySymbol.THB, false)),
            Map.entry("hkd", new CurrencyUtil(CurrencySymbol.HKD, false)),
            Map.entry("sar", new CurrencyUtil(CurrencySymbol.SAR, false)),
            Map.entry("omr", new CurrencyUtil(CurrencySymbol.OMR, false)),
            Map.entry("qar", new CurrencyUtil(CurrencySymbol.QAR, false)),
            Map.entry("kwd", new CurrencyUtil(CurrencySymbol.KWD, false)),
            Map.entry("bhd", new CurrencyUtil(CurrencySymbol.BHD, false)),
            Map.entry("myr", new CurrencyUtil(CurrencySymbol.MYR, false)),
            Map.entry("cny", new CurrencyUtil(CurrencySymbol.CNY, false)),
            Map.entry("jpy", new CurrencyUtil(CurrencySymbol.JPY, false)),
            Map.entry("sgd", new CurrencyUtil(CurrencySymbol.SGD, false)),
            Map.entry("nzd", new CurrencyUtil(CurrencySymbol.NZD, false)),
            Map.entry("tmt", new CurrencyUtil(CurrencySymbol.TMT, false)),

            // Metals
            Map.entry("sekkeh", new CurrencyUtil(CurrencySymbol.EMAMI_COIN, true)),
            Map.entry("bahar", new CurrencyUtil(CurrencySymbol.BAHAR_AZADI_COIN, true)),
            Map.entry("nim", new CurrencyUtil(CurrencySymbol.HALF_COIN, true)),
            Map.entry("rob", new CurrencyUtil(CurrencySymbol.QUARTER_COIN, true)),
            Map.entry("gerami", new CurrencyUtil(CurrencySymbol.ONE_GRAM_COIN, true)),
            Map.entry("18ayar", new CurrencyUtil(CurrencySymbol.GOLD_18K, false)),
            Map.entry("silver_999", new CurrencyUtil(CurrencySymbol.SILVER_999, true))
    );


    @Override
    public List<Currency> fetchCurrencies() {
        Instant instant = Instant.now();

        Map<String, CurrencyDto> response = restClient
                .get()
                .uri("/latest?api_key=" + apiKey)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return response.entrySet()
                .stream()
                .filter(entry -> SYMBOLS.containsKey(entry.getKey()))
                .map(entry -> {
                    CurrencyUtil currency = SYMBOLS.get(entry.getKey());
                    String rawPrice = entry.getValue().value.replace(".","");
                    String price = currency.isRial() ? rawPrice + "0000" : rawPrice + "0";
                    String symbol = currency.symbol().name();

                    return new Currency(
                            symbol,
                            price,
                            instant
                    );
                })
                .toList();
    }

    private record CurrencyUtil(CurrencySymbol symbol, boolean isRial) {}
    private record CurrencyDto(String value){}
}
