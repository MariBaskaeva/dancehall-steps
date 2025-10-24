package ru.baskaeva.dancehallsteps.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.baskaeva.dancehallsteps.model.Era;
import ru.baskaeva.dancehallsteps.model.Step;
import ru.baskaeva.dancehallsteps.model.Tag;
import ru.baskaeva.dancehallsteps.model.Type;
import ru.baskaeva.dancehallsteps.repository.StepRepository;
import ru.baskaeva.dancehallsteps.repository.TagRepository;
import ru.baskaeva.dancehallsteps.specification.StepSpecifications;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepService {
    private final StepRepository stepRepository;
    private final TagRepository tagRepository;

    public Page<Step> receiveSteps(String name,
                                   Type type,
                                   Era era,
                                   String author,
                                   List<String> tagNames,
                                   Pageable pageable) {
        List<Tag> tags = (tagNames == null || tagNames.isEmpty()) ? null : tagRepository.findByNameIn(tagNames);

        Specification<Step> spec = (root, query, cb) -> cb.conjunction();
        spec = spec.and(StepSpecifications.hasName(name))
                .and(StepSpecifications.hasType(type))
                .and(StepSpecifications.hasEra(era))
                .and(StepSpecifications.hasAuthor(author))
                .and(StepSpecifications.hasTags(tags));

        return stepRepository.findAll(spec, pageable);
    }
}
