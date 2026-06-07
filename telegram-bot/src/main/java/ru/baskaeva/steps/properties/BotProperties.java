package ru.baskaeva.steps.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties("bot")
public record BotProperties(
        String token,
        String name,
        Resource greeting,
        ProxyProperties proxy) {
}
