package ru.baskaeva.dancehallsteps.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Step {
    @Id
    @SequenceGenerator(
            name = "step-sequence",
            sequenceName = "step_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "step-sequence")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String author;
    @Enumerated(EnumType.STRING)
    private Era era;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "step_tags",
            joinColumns = @JoinColumn(name = "step_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
}
