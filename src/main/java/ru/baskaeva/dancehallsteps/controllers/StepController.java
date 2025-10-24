package ru.baskaeva.dancehallsteps.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.baskaeva.dancehallsteps.model.Step;
import ru.baskaeva.dancehallsteps.services.StepService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/steps")
public class StepController {
    private final StepService service;

    @GetMapping
    private Page<Step> getSteps(@RequestParam Integer page) {
        return service.receiveSteps(page);
    }
}
