package ru.baskaeva.steps.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.baskaeva.steps.routing.BotRequest;
import ru.baskaeva.steps.properties.BotProperties;
import ru.baskaeva.steps.routing.BotResponse;
import ru.baskaeva.steps.routing.Dispatcher;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final BotProperties botProperties;
    private final Dispatcher dispatcher;

    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
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
            throw new RuntimeException("Telegram API error", e);
        }
    }
}