package ru.baskaeva.steps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.baskaeva.steps.properties.AnalyticsProperties;
import ru.baskaeva.steps.properties.BotProperties;

@Configuration
public class ApplicationConfig {
    @Bean
    @ConfigurationProperties("bot")
    public BotProperties botProperties() {
        return new BotProperties();
    }

    @Bean
    @ConfigurationProperties("analytics")
    public AnalyticsProperties analyticsProperties() {
        return new AnalyticsProperties();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(LongPollingBot bot) throws TelegramApiException {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }
}
