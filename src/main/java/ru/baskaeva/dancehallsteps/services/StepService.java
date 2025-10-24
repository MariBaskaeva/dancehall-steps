package ru.baskaeva.dancehallsteps.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.baskaeva.dancehallsteps.model.Step;
import ru.baskaeva.dancehallsteps.repository.StepRepository;

@Service
@RequiredArgsConstructor
public class StepService {
    private static final int SIZE = 10;
    private final StepRepository repository;

    public Page<Step> receiveSteps(int page) {
        Pageable pageable = PageRequest.of(page, SIZE);
        return repository.findAll(pageable);
    }
}
