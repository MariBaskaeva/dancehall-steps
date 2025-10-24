package ru.baskaeva.dancehallsteps.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baskaeva.dancehallsteps.DTO.StepsDTO;
import ru.baskaeva.dancehallsteps.model.Step;
import ru.baskaeva.dancehallsteps.repository.StepRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepService {
    private final StepRepository repository;

    public StepsDTO receiveSteps() {
        List<Step> steps = repository.findAll();
        Integer count = steps.size();
        return new StepsDTO(count, steps);
    }
}
