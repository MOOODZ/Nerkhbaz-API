package ir.moodz.nerkhbazapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "live_currencies")
public class Live {
    @Id
    private String symbol;
    private String price;
    private Instant createdAt;

    public Live(String symbol, String price, Instant createdAt) {
        this.symbol = symbol;
        this.price = price;
        this.createdAt = createdAt;
    }
}
