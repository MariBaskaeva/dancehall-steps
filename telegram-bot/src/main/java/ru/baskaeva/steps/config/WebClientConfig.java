package ru.baskaeva.steps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.baskaeva.steps.properties.AnalyticsProperties;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(AnalyticsProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.getProviders().getPosthog().getHost())
                .build();
    }
}