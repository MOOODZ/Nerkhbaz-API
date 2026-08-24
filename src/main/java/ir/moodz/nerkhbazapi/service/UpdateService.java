package ir.moodz.nerkhbazapi.service;

import ir.moodz.nerkhbazapi.model.collection.ClientUpdateStateCollection;
import ir.moodz.nerkhbazapi.repository.ClientUpdateStateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateService {
    private final ClientUpdateStateRepository repository;

    UpdateService(ClientUpdateStateRepository repository) {
        this.repository = repository;
    }

    public void updateCurrentState(
            String versionCode,
            String updateUrl
    ) {
        if (versionCode.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Version code can not be empty");
        }
        if (updateUrl.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update URL can not be empty");
        }

        ClientUpdateStateCollection updateState = new ClientUpdateStateCollection(versionCode,updateUrl);
        repository.save(updateState);
    }

//    public UpdateStateResponse checkUpdate(String versionCode) {
//        ClientUpdateStateCollection currentState = repository.findById(1).orElse(null);
//
//    }


}
