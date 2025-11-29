package ru.baskaeva.dancehallsteps.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tag {
    @Id
    @SequenceGenerator(
            name = "tag-sequence",
            sequenceName = "tag_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag-sequence")
    private Long id;
    private String name;
}