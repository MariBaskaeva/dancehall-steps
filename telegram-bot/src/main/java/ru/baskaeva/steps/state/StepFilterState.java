package ru.baskaeva.steps.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.baskaeva.steps.dto.Era;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepFilterState implements Serializable {
    private List<String> names = new ArrayList<>();
    private Set<String> authors = new HashSet<>();
    private Set<Era> eras = new HashSet<>();

    public String buildFilterText() {
        StringBuilder sb = new StringBuilder("Текущий фильтр\n\n");

        if (names.isEmpty()) {
            sb.append("Названия: любые\n");
        } else {
            sb.append("Названия выбраны:\n")
                    .append(String.join("\n", names))
                    .append("\n");
        }

        if (authors.isEmpty()) {
            sb.append("Авторы: любые\n");
        } else {
            sb.append("Авторы выбраны: \n").append(String.join("\n", authors)).append("\n");
        }

        if (eras.isEmpty()) {
            sb.append("Эры: любые\n");
        } else {
            sb.append("Эры выбраны: \n")
                    .append(eras.stream().map(Enum::toString).collect(Collectors.joining("\n")))
                    .append("\n");
        }

        return sb.toString();
    }
}