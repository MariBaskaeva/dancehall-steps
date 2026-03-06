package ru.baskaeva.steps.analytics.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.baskaeva.steps.analytics.AnalyticsEvent;
import ru.baskaeva.steps.analytics.AnalyticsProvider;
import ru.baskaeva.steps.analytics.db.BotEvent;
import ru.baskaeva.steps.analytics.db.BotEventRepository;
import ru.baskaeva.steps.properties.AnalyticsProperties;

@RequiredArgsConstructor
@Service
public class DatabaseAnalyticsProvider implements AnalyticsProvider {
    private final BotEventRepository repo;
    private final AnalyticsProperties properties;

    @Override
    public void track(AnalyticsEvent event) {
        if (!properties.getProviders().getDb().getEnabled()) return;

        BotEvent e = new BotEvent();
        e.setEventName(event.getName().value());
        e.setDistinctId(event.getDistinctId());
        e.setUiVersion(event.getUiVersion());
        e.setProps(event.getProps());

        repo.save(e);
    }
}