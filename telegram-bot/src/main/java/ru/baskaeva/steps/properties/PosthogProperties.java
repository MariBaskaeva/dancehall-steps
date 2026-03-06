package ru.baskaeva.steps.properties;

import lombok.Data;

@Data
public class PosthogProperties {
    Boolean enabled;
    String apiKey;
    String host;
}