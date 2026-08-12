package ir.moodz.nerkhbazapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "history_currencies")
public class History {
    @Id
    ObjectId id;
    private String symbol;
    private String price;
    private Instant createdAt;

    public History(String symbol, String price, Instant createdAt) {
        this.id = ObjectId.get();
        this.symbol = symbol;
        this.price = price;
        this.createdAt = createdAt;
    }
}
