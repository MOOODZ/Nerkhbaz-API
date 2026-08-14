package ir.moodz.nerkhbazapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(value = "history_currencies")
public class HistoryEntity {
    @Id
    ObjectId id;
    private String symbol;
    private String price;
    private Instant createdAt;

    public HistoryEntity(String symbol, String price, Instant createdAt) {
        this.id = ObjectId.get();
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
