package ru.baskaeva.steps.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.baskaeva.steps.dto.StepDTO;
import ru.baskaeva.steps.dto.StepFilter;
import ru.baskaeva.steps.mapper.StepMapper;
import ru.baskaeva.steps.model.Step;
import ru.baskaeva.steps.repository.StepRepository;

@Service
@RequiredArgsConstructor
public class StepServiceImpl implements StepService {
    private final StepRepository stepRepository;
    private final StepMapper mapper;

    @Override
    public Page<StepDTO> findAll(StepFilter filter,
                                 Pageable pageable) {

        return stepRepository.findAll(filter.toSpecification(), pageable)
                .map(mapper::toStepDTO);
    }

    @Override
    public StepDTO create(StepDTO stepDTO) {
        Step step = mapper.toStep(stepDTO);
        return mapper.toStepDTO(stepRepository.save(step));
    }
}
