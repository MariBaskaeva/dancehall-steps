package ru.baskaeva.steps.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baskaeva.steps.dto.StepDTO;
import ru.baskaeva.steps.dto.StepFilter;
import ru.baskaeva.steps.services.StepService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/steps")
public class StepController {
    private final StepService service;

    @GetMapping
    private PagedModel<StepDTO> getSteps(@ModelAttribute StepFilter filter,
                                         Pageable pageable) {
        return new PagedModel<>(service.findAll(filter, pageable));
    }

    @PostMapping
    private StepDTO postStep(@RequestBody StepDTO stepDTO) {
        return service.create(stepDTO);
    }
}
