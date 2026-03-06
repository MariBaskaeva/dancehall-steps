package ru.baskaeva.steps.properties;

import lombok.Data;

@Data
public class AnalyticsProperties {
    String uiVersion;
    ProviderProperties providers;

}
