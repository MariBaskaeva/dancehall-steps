package ru.baskaeva.steps.mapper;

import org.mapstruct.Mapper;
import ru.baskaeva.steps.dto.StepDTO;
import ru.baskaeva.steps.dto.TagDTO;
import ru.baskaeva.steps.model.Step;
import ru.baskaeva.steps.model.Tag;

@Mapper(componentModel = "spring")
public interface StepMapper {
    StepDTO toStepDTO(Step step);
    TagDTO toTagDTO(Tag tag);
    Step toStep(StepDTO stepDTO);
    Tag toTag(TagDTO tagDTO);
}
