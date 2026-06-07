package ru.baskaeva.steps.ui;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.baskaeva.steps.dto.AuthorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AuthorsKeyboardFactory {
    public InlineKeyboardMarkup create(int currentPage,
                                       Page<AuthorDTO> authors,
                                       Integer messageId,
                                       String text,
                                       Set<String> selectedAuthors) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        PaginationKeyboardFactory factory = new PaginationKeyboardFactory();

        List<AuthorDTO> authorDTOList = authors.getContent();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (int i = 0; i < authorDTOList.size(); i += 2) {
            List<InlineKeyboardButton> row = new ArrayList<>();

            for (int j = i; j < i + 2 && j < authorDTOList.size(); j++) {
                AuthorDTO author = authorDTOList.get(j);

                boolean selected = selectedAuthors.contains(author.name());

                InlineKeyboardButton btn = new InlineKeyboardButton();
                btn.setText((selected ? "✅" : "") + author.name());
                btn.setCallbackData("filter:author:" + author.name() + ":" + currentPage);
                row.add(btn);
            }

            keyboard.add(row);
        }
        var paginationButtons = factory.create(currentPage, authors.getTotalPages(), messageId, text).getKeyboard().get(0);
        keyboard.add(paginationButtons);
        keyboard.add(List.of(resetButton(), backToMenuButton()));
        markup.setKeyboard(keyboard);

        return markup;
    }

    private InlineKeyboardButton resetButton() {
        return InlineKeyboardButton.builder()
                .text("Сбросить")
                .callbackData("reset:authors")
                .build();
    }

    private InlineKeyboardButton backToMenuButton() {
        return InlineKeyboardButton.builder()
                .text("Назад")
                .callbackData("back_to_menu_btn")
                .build();
    }
}