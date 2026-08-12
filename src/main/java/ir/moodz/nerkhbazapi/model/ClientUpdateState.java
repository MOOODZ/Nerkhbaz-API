package ir.moodz.nerkhbazapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nerkhbaz_update_state")
public class ClientUpdateState {
    @Id
    private int id;
    private String versionCode;
    private String updateUrl;

    public ClientUpdateState(String versionCode, String updateUrl) {
        this.id = 1;
        this.versionCode = versionCode;
        this.updateUrl = updateUrl;
    }
}
