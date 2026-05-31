package ru.baskaeva.steps.ui;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuKeyboardFactory {
    public InlineKeyboardMarkup create() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> row1 = new ArrayList<>(1);
        List<InlineKeyboardButton> row2 = new ArrayList<>(2);
        List<InlineKeyboardButton> row3 = new ArrayList<>(2);

        row1.add(stepnameButton());

        row2.add(authorButton());
        row2.add(eraButton());

        row3.add(resetButton());
        row3.add(showstepsButton());

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        markup.setKeyboard(keyboard);

        return markup;
    }

    private InlineKeyboardButton stepnameButton() {
        return InlineKeyboardButton.builder()
                .text("Поиск по названию")
                .callbackData("filter:names")
                .build();
    }

    private InlineKeyboardButton authorButton() {
        return InlineKeyboardButton.builder()
                .text("Авторы")
                .callbackData("author_filter")
                .build();
    }

    private InlineKeyboardButton eraButton() {
        return InlineKeyboardButton.builder()
                .text("Эпохи")
                .callbackData("era_filter")
                .build();
    }

    private InlineKeyboardButton resetButton() {
        return InlineKeyboardButton.builder()
                .text("Сбросить")
                .callbackData("reset:all")
                .build();
    }

    private InlineKeyboardButton showstepsButton() {
        return InlineKeyboardButton.builder()
                .text("Показать степы")
                .callbackData("showsteps_btn")
                .build();
    }
}