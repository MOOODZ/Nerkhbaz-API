package ir.moodz.nerkhbazapi.model.dto;

public class UpdateStateResponse{
        private final boolean isUpdateNeeded;
        private final String updateUrl;

        UpdateStateResponse(
                boolean isUpdateNeeded,
                String updateUrl
        ){
            this.isUpdateNeeded = isUpdateNeeded;
            this.updateUrl = updateUrl;
        }

        public boolean isUpdateNeeded() {
            return isUpdateNeeded;
        }

        public String getUpdateUrl() {
            return updateUrl;
        }
    }