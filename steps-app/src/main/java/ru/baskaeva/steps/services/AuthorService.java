package ru.baskaeva.steps.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.baskaeva.steps.dto.AuthorDTO;

public interface AuthorService {
    Page<AuthorDTO> findAll(Pageable pageable);
}
