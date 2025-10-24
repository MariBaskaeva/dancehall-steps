package ru.baskaeva.dancehallsteps.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.baskaeva.dancehallsteps.model.Step;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepsDTO {
    Long totalCount;
    List<Step> steps;
}
