package ru.baskaeva.dancehallsteps.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.baskaeva.dancehallsteps.model.Step;

public interface StepRepository extends ListCrudRepository<Step, Long> {
}
