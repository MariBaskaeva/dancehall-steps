package ru.baskaeva.steps.analytics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.baskaeva.steps.routing.BotRequest;

import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    private final List<AnalyticsProvider> providers;
    private final String uiVersion;

    public AnalyticsService(
            List<AnalyticsProvider> providers,
            @Value("${analytics.ui-version:v1}") String uiVersion
    ) {
        this.providers = providers;
        this.uiVersion = uiVersion;
    }

    public void track(BotRequest req, AnalyticsEventName name, Map<String, Object> props) {
        if (req == null || req.userId() == null) {
            return;
        }

        AnalyticsEvent event = AnalyticsEvent.builder(
                        name,
                        req.userId().toString(),
                        uiVersion
                )
                .props(props)
                .build();

        for (AnalyticsProvider provider : providers) {
            try {
                provider.track(event);
            } catch (Exception ignored) {
            }
        }
    }
}