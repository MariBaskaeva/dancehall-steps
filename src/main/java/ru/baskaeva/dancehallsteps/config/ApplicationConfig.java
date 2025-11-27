package ru.baskaeva.dancehallsteps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.baskaeva.dancehallsteps.bot.Bot;
import ru.baskaeva.dancehallsteps.properties.BotProperties;

@Configuration
public class ApplicationConfig {
    @Bean
    @ConfigurationProperties("bot")
    public BotProperties botProperties() {
        return new BotProperties();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(LongPollingBot bot) throws TelegramApiException {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }
}
