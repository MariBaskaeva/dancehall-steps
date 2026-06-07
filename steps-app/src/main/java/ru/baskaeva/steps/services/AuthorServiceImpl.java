package ru.baskaeva.steps.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.baskaeva.steps.dto.AuthorDTO;
import ru.baskaeva.steps.mapper.StepMapper;
import ru.baskaeva.steps.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository repository;
    private final StepMapper mapper;

    @Override
    public Page<AuthorDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toAuthorDTO);
    }
}
