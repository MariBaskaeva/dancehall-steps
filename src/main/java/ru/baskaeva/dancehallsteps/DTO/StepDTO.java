package ru.baskaeva.dancehallsteps.DTO;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.baskaeva.dancehallsteps.model.Era;
import ru.baskaeva.dancehallsteps.model.Type;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class StepDTO {
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String author;
    @Enumerated(EnumType.STRING)
    private Era era;
    private List<TagDTO> tags = new ArrayList<>();
}
