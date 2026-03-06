package ru.baskaeva.steps.properties;

import lombok.Data;

@Data
public class ProviderProperties {
    DBProperties db;
    PosthogProperties posthog;
}
