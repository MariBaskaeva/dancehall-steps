package ru.baskaeva.dancehallsteps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baskaeva.dancehallsteps.model.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameIn(List<String> names);
}
