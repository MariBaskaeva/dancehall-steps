package ru.baskaeva.steps.ui;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaginationKeyboardFactory {

    public InlineKeyboardMarkup create(
            int currentPage,
            int totalPages,
            Integer messageId,
            String text
    ) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = new ArrayList<>(3);

        if (currentPage > 0) {
            row.add(navButton("◀️ Назад", messageId, text, currentPage - 1));
        }

        row.add(infoButton(currentPage, totalPages));

        if (currentPage < totalPages - 1) {
            row.add(navButton("Вперед ▶️", messageId, text, currentPage + 1));
        }

        markup.setKeyboard(List.of(row));
        return markup;
    }

    private InlineKeyboardButton navButton(String label, Integer messageId, String text, int page) {
        return InlineKeyboardButton.builder()
                .text(label)
                .callbackData("page_" + messageId + "_" + text + "_" + page)
                .build();
    }

    private InlineKeyboardButton infoButton(int currentPage, int totalPages) {
        return InlineKeyboardButton.builder()
                .text((currentPage + 1) + "/" + totalPages)
                .callbackData("noon")
                .build();
    }
}