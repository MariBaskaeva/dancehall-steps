package ru.baskaeva.steps.analytics.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.baskaeva.steps.analytics.AnalyticsEvent;
import ru.baskaeva.steps.analytics.AnalyticsProvider;
import ru.baskaeva.steps.properties.AnalyticsProperties;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PosthogAnalyticsProvider implements AnalyticsProvider {

    private final WebClient webClient;
    private final AnalyticsProperties properties;

    @Override
    public void track(AnalyticsEvent event) {
        var postHog = properties.getProviders().getPosthog();
        if (!postHog.getEnabled()) return;
        if (postHog.getApiKey() == null || postHog.getApiKey().isBlank()) return;

        Map<String, Object> properties = new HashMap<>(event.getProps());
        properties.put("distinct_id", event.getDistinctId());

        Map<String, Object> body = Map.of(
                "api_key", postHog.getApiKey(),
                "event", event.getName().value(),
                "distinct_id", event.getDistinctId(),
                "properties", properties
        );
        System.out.println("Sending event to PostHog: " + event.getName());
        webClient.post()
                .uri("/capture/")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(ex -> reactor.core.publisher.Mono.empty())
                .subscribe();
    }
}