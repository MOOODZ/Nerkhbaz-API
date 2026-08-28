package ir.moodz.nerkhbazapi.controller;

import ir.moodz.nerkhbazapi.service.UpdateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
public class UpdateController {

    private final UpdateService service;

    public UpdateController(UpdateService service) {
        this.service = service;
    }

    @GetMapping("/{versionCode}")
    public UpdateService.Response getState(@PathVariable String versionCode){
        return service.checkUpdate(versionCode);
    }

    @PostMapping
    public void setState(@RequestBody UpdateService.Request request){
        service.updateCurrentState(request);
    }

}
