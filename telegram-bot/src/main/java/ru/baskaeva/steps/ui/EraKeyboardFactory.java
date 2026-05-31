package ru.baskaeva.steps.ui;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.baskaeva.steps.dto.Era;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class EraKeyboardFactory {
    public InlineKeyboardMarkup create(int currentPage,
                                       Era[] eras,
                                       Integer messageId,
                                       String text,
                                       Set<Era> selectedEras) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        PaginationKeyboardFactory factory = new PaginationKeyboardFactory();

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (int i = 0; i < eras.length; i += 2) {
            List<InlineKeyboardButton> row = new ArrayList<>();

            for (int j = i; j < i + 2 && j < eras.length; j++) {
                Era era = eras[j];

                boolean selected = selectedEras.contains(era);

                InlineKeyboardButton btn = new InlineKeyboardButton();
                btn.setText((selected ? "✅" : "") + era.name());
                btn.setCallbackData("filter:era:" + era.name() + ":" + currentPage);
                row.add(btn);
            }

            keyboard.add(row);
        }
        keyboard.add(List.of(resetButton(), backToMenuButton()));
        markup.setKeyboard(keyboard);

        return markup;
    }

    private InlineKeyboardButton resetButton() {
        return InlineKeyboardButton.builder()
                .text("Сбросить")
                .callbackData("reset:eras")
                .build();
    }

    private InlineKeyboardButton backToMenuButton() {
        return InlineKeyboardButton.builder()
                .text("Назад")
                .callbackData("back_to_menu_btn")
                .build();
    }
}