package ru.baskaeva.dancehallsteps.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baskaeva.dancehallsteps.DTO.StepsDTO;
import ru.baskaeva.dancehallsteps.services.StepService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/steps")
public class StepController {
    private final StepService service;

    @GetMapping
    private StepsDTO getSteps() {
        return service.receiveSteps();
    }
}
