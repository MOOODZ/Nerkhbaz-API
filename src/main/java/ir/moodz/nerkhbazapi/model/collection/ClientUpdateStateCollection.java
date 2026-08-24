package ir.moodz.nerkhbazapi.model.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("nerkhbaz_update_state")
public class ClientUpdateStateCollection {
    @Id
    private final int id = 1;
    private String versionCode;
    private String updateUrl;

    public ClientUpdateStateCollection(String versionCode, String updateUrl) {
        this.versionCode = versionCode;
        this.updateUrl = updateUrl;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }
}
