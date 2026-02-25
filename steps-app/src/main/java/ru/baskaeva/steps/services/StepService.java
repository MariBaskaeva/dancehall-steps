package ru.baskaeva.steps.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.baskaeva.steps.dto.StepDTO;
import ru.baskaeva.steps.dto.StepFilter;

public interface StepService {
    Page<StepDTO> findAll(StepFilter filter, Pageable pageable);
    StepDTO create(StepDTO stepDTO);
}
