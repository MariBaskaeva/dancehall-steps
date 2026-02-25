package ru.baskaeva.steps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.baskaeva.steps.model.Step;

public interface StepRepository extends JpaRepository<Step, Long>, JpaSpecificationExecutor<Step> {
}
