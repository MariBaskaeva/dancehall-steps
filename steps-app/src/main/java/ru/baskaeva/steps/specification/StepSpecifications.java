package ru.baskaeva.steps.specification;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import ru.baskaeva.steps.model.Era;
import ru.baskaeva.steps.model.Step;
import ru.baskaeva.steps.model.Tag;
import ru.baskaeva.steps.model.Type;

import java.util.List;
import java.util.Optional;

public class StepSpecifications {
    public static Specification<Step> hasName(@Nullable String name) {
        return (root, query, cb) -> Optional.ofNullable(name)
                .map(n -> cb.like(cb.lower(root.get("name")), "%" + n.toLowerCase() + "%"))
                        .orElseGet(cb::conjunction);
    }

    public static Specification<Step> hasType(@Nullable Type type) {
        return (root, query, cb) -> Optional.ofNullable(type)
                .map(t -> cb.equal(root.get("type"), t))
                .orElseGet(cb::conjunction);
    }

    public static Specification<Step> hasAuthor(@Nullable String author) {
        return (root, query, cb) -> Optional.ofNullable(author)
                .map(a -> cb.like(cb.lower(root.get("author")), "%" + a.toLowerCase() + "%"))
                .orElseGet(cb::conjunction);
    }

    public static Specification<Step> hasEra(@Nullable Era era) {
        return (root, query, cb) -> Optional.ofNullable(era)
                .map(e -> cb.equal(root.get("era"), e))
                .orElseGet(cb::conjunction);
    }

    public static Specification<Step> hasTags(@Nullable List<Tag> tags) {
        return (root, query, cb) -> Optional.ofNullable(tags)
                .map(t -> {
                    query.distinct(true);
                    return root.join("tags", JoinType.INNER).in(t);
                })
                .orElseGet(cb::conjunction);
    }
}

