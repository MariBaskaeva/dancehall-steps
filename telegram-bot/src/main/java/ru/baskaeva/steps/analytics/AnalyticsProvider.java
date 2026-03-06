package ru.baskaeva.steps.analytics;

public interface AnalyticsProvider {
    void track(AnalyticsEvent event);
}