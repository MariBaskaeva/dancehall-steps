package ru.baskaeva.steps.config;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.baskaeva.steps.properties.BotProperties;
import ru.baskaeva.steps.properties.ProxyProperties;

@Configuration
public class ApplicationConfig {

    @Bean
    DefaultBotOptions options(BotProperties botProperties) {
        DefaultBotOptions options = new DefaultBotOptions();
        ProxyProperties proxy = botProperties.proxy();
        if (proxy != null) {
            options.setProxyType(proxy.type());
            options.setProxyHost(proxy.host());
            options.setProxyPort(proxy.port());
            configureProxyAuthentication(proxy);
        }
        return options;
    }

    private void configureProxyAuthentication(ProxyProperties proxy) {
        if (isBlank(proxy.username()) || isBlank(proxy.password())) {
            return;
        }

        System.setProperty("java.net.socks.username", proxy.username());
        System.setProperty("java.net.socks.password", proxy.password());

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestorType() == RequestorType.PROXY || isConfiguredProxy()) {
                    return new PasswordAuthentication(proxy.username(), proxy.password().toCharArray());
                }
                return null;
            }

            private boolean isConfiguredProxy() {
                return proxy.host().equalsIgnoreCase(getRequestingHost()) && proxy.port() == getRequestingPort();
            }
        });
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(LongPollingBot bot) throws TelegramApiException {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }
}
