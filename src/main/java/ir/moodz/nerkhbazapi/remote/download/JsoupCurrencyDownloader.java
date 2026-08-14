package ir.moodz.nerkhbazapi.remote.download;

import ir.moodz.nerkhbazapi.domain.Currency;
import ir.moodz.nerkhbazapi.domain.CurrencySymbol;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsoupCurrencyDownloader implements CurrencyDownload {

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
            if (flag == null || flag.parent() == null) {
                continue;
            }

            Element row = flag.parent().parent();

            if (row == null) {
                continue;
            }
            String price = row.text().replace(",","");

            Currency currencyData = new Currency(currencySymbol.name(), price, null);
            currencies.add(currencyData);
        }

        return currencies;
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
