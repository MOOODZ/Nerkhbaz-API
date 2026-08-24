package ir.moodz.nerkhbazapi.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(value = "live_currencies")
public class LiveCollection {
    @Id
    private String symbol;
    private final String price;
    private final Instant createAt;

    public LiveCollection(String symbol, String price, Instant createAt) {
        this.symbol = symbol;
        this.price = price;
        this.createAt = createAt;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public Instant getCreateAt() {
        return createAt;
    }
}
