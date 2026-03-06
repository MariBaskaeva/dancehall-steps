package ru.baskaeva.steps.analytics;

public enum AnalyticsEventName {
    BOT_STARTED("bot_started"),
    SEARCH_STARTED("search_started"),
    FILTER_OPENED("filter_opened"),
    FILTER_APPLIED("filter_applied"),
    RESULTS_SHOWN("results_shown"),
    NO_RESULTS("no_results"),
    UNKNOWN_INPUT("unknown_input");

    private final String value;

    AnalyticsEventName(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}