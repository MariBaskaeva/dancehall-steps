package ru.baskaeva.steps.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.baskaeva.steps.properties.BotProperties;
import ru.baskaeva.steps.routing.BotRequest;
import ru.baskaeva.steps.routing.BotResponse;
import ru.baskaeva.steps.routing.Dispatcher;

@Component
public class Bot extends TelegramLongPollingBot {
    private final BotProperties botProperties;
    private final Dispatcher dispatcher;

    public Bot(BotProperties botProperties, Dispatcher dispatcher, DefaultBotOptions botOptions) {
        super(botOptions);
        this.botProperties = botProperties;
        this.dispatcher = dispatcher;
    }

    @Override
    public String getBotUsername() {
        return botProperties.name();
    }

    @Override
    public String getBotToken() {
        return botProperties.token();
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotRequest request = new BotRequest(update);
        dispatcher.dispatch(request)
                .ifPresent(this::executeSafely);
    }

    private void executeSafely(BotResponse response) {
        try {
            execute(response.method());
        } catch (TelegramApiException e) {
            String message = e.getMessage();

            if (message != null && message.contains("message is not modified")) {
                return;
            }

            throw new RuntimeException("Telegram API error", e);
        }
    }
}