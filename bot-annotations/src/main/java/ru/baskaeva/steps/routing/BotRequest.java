package ru.baskaeva.steps.routing;

import org.telegram.telegrambots.meta.api.objects.Update;

public record BotRequest(Update update) {

    public boolean hasText() {
        return update.hasMessage() && update.getMessage().hasText();
    }

    public String text() {
        return update.getMessage().getText();
    }

    public boolean isCommand(String cmd) {
        return hasText() && update.getMessage().isCommand() && text().equals(cmd);
    }

    public boolean hasCallback() {
        return update.hasCallbackQuery();
    }

    public String callback() {
        return update.getCallbackQuery().getData();
    }

    public Long chatId() {
        if (hasCallback()) return update.getCallbackQuery().getMessage().getChatId();
        return update.getMessage().getChatId();
    }

    public Integer messageId() {
        if (hasCallback()) return update.getCallbackQuery().getMessage().getMessageId();
        return update.getMessage().getMessageId();
    }

    public Long userId() {
        if (hasCallback()) return update.getCallbackQuery().getFrom().getId();
        return update.getMessage().getFrom().getId();
    }
}