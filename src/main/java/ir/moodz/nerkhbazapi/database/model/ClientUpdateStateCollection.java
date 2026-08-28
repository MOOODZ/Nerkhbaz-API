package ir.moodz.nerkhbazapi.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("nerkhbaz_update_state")
public class ClientUpdateStateCollection {
    @Id
    private final int id;
    private final String versionCode;
    private final String updateUrl;

    public ClientUpdateStateCollection(
            int id,
            String versionCode,
            String updateUrl
    ) {
        this.id = id;
        this.versionCode = versionCode;
        this.updateUrl = updateUrl;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

}
