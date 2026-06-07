package ru.baskaeva.steps.properties;

import org.telegram.telegrambots.bots.DefaultBotOptions;

public record ProxyProperties(
        DefaultBotOptions.ProxyType type,
        String host,
        int port,
        String username,
        String password) {
}
