package ru.baskaeva.steps.dto;

import ru.baskaeva.steps.model.Author;

import java.util.List;

public record StepDTO (String name, Type type, Author author, Era era, List<TagDTO> tags) {

}
