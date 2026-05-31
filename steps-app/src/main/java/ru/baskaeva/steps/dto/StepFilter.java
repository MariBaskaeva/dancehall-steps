package ru.baskaeva.steps.dto;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.baskaeva.steps.model.Step;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepFilter {
    private List<String> names;
    private Type type;
    private Set<Era> era;
    private Set<String> author;
    private List<String> tagNames;

    public Specification<Step> toSpecification() {
        return Specification.allOf(
                namesSpec(),
                typeSpec(),
                eraSpec(),
                authorSpec(),
                tagsSpec()
        );
    }

    private Specification<Step> namesSpec() {
        return (root, query, cb) -> Optional.ofNullable(names)
                .filter(n -> !n.isEmpty())
                .map(n -> cb.or(
                        n.stream()
                                .map(name -> cb.like(
                                        cb.lower(root.get("name")),
                                        "%" + name.toLowerCase() + "%"
                                ))
                                .toArray(Predicate[]::new)
                ))
                .orElseGet(cb::conjunction);
    }

    private Specification<Step> typeSpec() {
        return (root, query, cb) -> Optional.ofNullable(type)
                .map(t -> cb.equal(root.get("type"), t))
                .orElseGet(cb::conjunction);
    }

    private Specification<Step> authorSpec() {
        return (root, query, cb) -> Optional.ofNullable(author)
                .filter(a -> !a.isEmpty())
                .map(e -> root.get("author").get("name").in(e))
                .orElseGet(cb::conjunction);
    }

    private Specification<Step> eraSpec() {
        return (root, query, cb) -> Optional.ofNullable(era)
                .filter(e -> !e.isEmpty())
                .map(e -> root.get("era").in(e))
                .orElseGet(cb::conjunction);
    }

    private Specification<Step> tagsSpec() {
        return (root, query, cb) -> Optional.ofNullable(tagNames)
                .map(t -> {
                    query.distinct(true);
                    return root.join("tags", JoinType.INNER).get("name").in(t);
                })
                .orElseGet(cb::conjunction);
    }
}
