package ru.baskaeva.steps.dto;

import jakarta.persistence.criteria.JoinType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.baskaeva.steps.model.Step;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepFilter {
    private String name;
    private Type type;
    private Era era;
    private String author;
    private List<String> tagNames;

    public Specification<Step> toSpecification() {
        return Specification.allOf(
                nameSpec(),
                typeSpec(),
                eraSpec(),
                authorSpec(),
                tagsSpec()
        );
    }

    private Specification<Step> nameSpec() {
        return (root, query, cb) -> Optional.ofNullable(name)
                .map(n -> cb.like(cb.lower(root.get("name")), "%" + n.toLowerCase() + "%"))
                .orElseGet(cb::conjunction);
    }

    private Specification<Step> typeSpec() {
        return (root, query, cb) -> Optional.ofNullable(type)
                .map(t -> cb.equal(root.get("type"), t))
                .orElseGet(cb::conjunction);
    }

    private Specification<Step> authorSpec() {
        return (root, query, cb) -> Optional.ofNullable(author)
                .map(a -> cb.like(cb.lower(root.get("author")), "%" + a.toLowerCase() + "%"))
                .orElseGet(cb::conjunction);
    }

    private Specification<Step> eraSpec() {
        return (root, query, cb) -> Optional.ofNullable(era)
                .map(e -> cb.equal(root.get("era"), e))
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
