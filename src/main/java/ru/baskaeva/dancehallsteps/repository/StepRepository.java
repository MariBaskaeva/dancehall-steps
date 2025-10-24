package ru.baskaeva.dancehallsteps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baskaeva.dancehallsteps.model.Step;

public interface StepRepository extends JpaRepository<Step, Long> {
}
