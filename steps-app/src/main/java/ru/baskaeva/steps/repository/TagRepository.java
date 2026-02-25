package ru.baskaeva.steps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baskaeva.steps.model.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameIn(List<String> names);
}
