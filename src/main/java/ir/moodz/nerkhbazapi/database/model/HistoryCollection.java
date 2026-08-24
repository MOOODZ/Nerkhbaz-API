package ir.moodz.nerkhbazapi.database.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(value = "history_currencies")
public class HistoryCollection {
    @Id
    private ObjectId id = ObjectId.get();
    private final String symbol;
    private final String price;
    private final Instant createAt;

    public HistoryCollection(String symbol, String price, Instant createAt) {
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
