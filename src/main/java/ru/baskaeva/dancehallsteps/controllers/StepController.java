package ru.baskaeva.dancehallsteps.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.baskaeva.dancehallsteps.model.Era;
import ru.baskaeva.dancehallsteps.model.Step;
import ru.baskaeva.dancehallsteps.model.Type;
import ru.baskaeva.dancehallsteps.services.StepService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/steps")
public class StepController {
    private final StepService service;

    @GetMapping
    private Page<Step> getSteps(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Type type,
                                @RequestParam(required = false) Era era,
                                @RequestParam(required = false) String author,
                                @RequestParam(required = false) List<String> tagNames,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "name") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return service.receiveSteps(name, type, era, author, tagNames, pageable);
    }
}
