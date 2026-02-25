package ru.baskaeva.steps.dto;

import java.util.List;

public record StepDTO (String name, Type type, String author, Era era, List<TagDTO> tags) {

}
