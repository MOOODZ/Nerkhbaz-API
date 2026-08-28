package ir.moodz.nerkhbazapi.remote.download;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.domain.CurrencySymbol;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsoupCurrencyDownloader implements CurrencyDownloader {

    private final Logger log = LoggerFactory.getLogger(JsoupCurrencyDownloader.class);
    private static final String USER_AGENT = "Nerkhbaz/2.0";
    private static final int TIMEOUT = 15000;

    @Override
    public List<Currency> fetch(String url, Map<CurrencySymbol, String> suffix) {
        Document document = download(url);
        var currencies = new ArrayList<Currency>();

        for (Map.Entry<CurrencySymbol, String> entry : suffix.entrySet()) {
            CurrencySymbol currencySymbol = entry.getKey();
            String suf = entry.getValue();

            Element flag = document.selectFirst("span.flag" + suf);
            Currency currencyData = getCurrencyData(flag, currencySymbol);
            currencies.add(currencyData);
        }

        return currencies;
    }

    private static @NonNull Currency getCurrencyData(Element flag, CurrencySymbol currencySymbol) {
        if (flag == null || flag.parent() == null) {
            throw new  IllegalArgumentException("HTML flag can not be null");
        }
        Element row = flag.parent().parent();

        if (row == null) {
            throw new  IllegalArgumentException("HTML row can not be null");
        }
        String price = row.text().replace(",","");

        return new Currency(currencySymbol.name(), price, null);
    }


    private Document download(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    .timeout(TIMEOUT)
                    .get();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to download: " + url, exception);
        }
    }

}
