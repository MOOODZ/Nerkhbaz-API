package ir.moodz.nerkhbazapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(value = "live_currencies")
public class LiveEntity {
    @Id
    private String symbol;
    private String price;
    private Instant createdAt;

    public LiveEntity(String symbol, String price, Instant createdAt) {
        this.symbol = symbol;
        this.price = price;
        this.createdAt = createdAt;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice() {
        return price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
