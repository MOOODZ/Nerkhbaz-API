package ir.moodz.nerkhbazapi.service;

import ir.moodz.nerkhbazapi.database.model.ClientUpdateStateCollection;
import ir.moodz.nerkhbazapi.repository.ClientUpdateStateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateService {

    private final Logger log = LoggerFactory.getLogger(UpdateService.class);
    private final ClientUpdateStateRepository repository;

    UpdateService(ClientUpdateStateRepository repository) {
        this.repository = repository;
    }

    public void updateCurrentState(Request request) {
        log.info("Request: {}", request.toString());

        if (request.versionCode().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Version code can not be empty");
        }
        if (request.updateUrl().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update URL can not be empty");
        }

        ClientUpdateStateCollection updateState = new ClientUpdateStateCollection(
                1,
                request.versionCode,
                request.updateUrl
        );
        repository.save(updateState);
    }

    public Response checkUpdate(String versionCode) {
        ClientUpdateStateCollection currentState = repository.findById(1).orElse(null);

        if (currentState == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Implement the init state first");
        }

        if (!versionCode.chars().allMatch(Character::isDigit)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Version Code must be a digit");
        }

        boolean isUpdateNeeded = Integer.parseInt(versionCode) < Integer.parseInt(currentState.getVersionCode());

        return new Response(isUpdateNeeded, currentState.getUpdateUrl());
    }

    public record Request(String versionCode, String updateUrl) {}

    public record Response(boolean isUpdateNeeded, String updateUrl) {}
}
