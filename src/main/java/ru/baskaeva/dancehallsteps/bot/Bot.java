package ru.baskaeva.dancehallsteps.bot;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.baskaeva.dancehallsteps.model.Step;
import ru.baskaeva.dancehallsteps.properties.BotProperties;
import ru.baskaeva.dancehallsteps.services.StepService;
import ru.baskaeva.dancehallsteps.tools.MyFileReader;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final StepService service;
    private final BotProperties botProperties;
    private boolean start = false;
    private final static int ITEMS_PER_PAGE = 3;

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            var msg = update.getMessage();
            var user = msg.getFrom();
            var text = msg.getText();

            if (msg.isCommand()) {
                if (text.equals("/start"))
                    start = true;
            }

            if (start) {
                var path = "static/start";
                sendText(user.getId(), MyFileReader.readFile(path), true);
            } else {
                Pageable pageable = PageRequest.of(0, ITEMS_PER_PAGE, Sort.by("name").ascending());
                Page<Step> steps;
                if (text.startsWith("author:")) {
                    var author = text.replaceAll("author: ", "");
                    steps = service.receiveSteps(null, null, null, author, null, pageable);
                    if (steps.isEmpty()) {
                        sendText(user.getId(), "Степы по автору " + author + " не найдены.", false);
                        return;
                    }
                    sendPage(user.getId(), createAnswer(author, "Степы по автору", steps), false, steps.getTotalPages(), msg.getMessageId(), text);
                } else if (text.startsWith("step:")) {
                    var step = text.replaceAll("step: ", "");
                    steps = service.receiveSteps(step, null, null, null, null, pageable);
                    if (steps.isEmpty()) {
                        sendText(user.getId(), "Степы по названию " + text.replaceAll("step: ", "") + " не найдены.", false);
                        return;
                    }
                    sendPage(user.getId(), createAnswer(step, "Степы по названию", steps), false, steps.getTotalPages(), msg.getMessageId(), text);
                } else if (text.equals("allSteps")) {
                    steps = service.receiveSteps(null, null, null, null, null, pageable);
                    sendPage(user.getId(), createAnswer(null, "Все степы", steps), false, steps.getTotalPages(), msg.getMessageId(), text);
                } else
                    sendText(user.getId(), "Не понял...", false);
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();

            if (callbackData.startsWith("page_")) {
                var splittedData = callbackData.split("_");
                var msgText = splittedData[2];
                int page = Integer.parseInt(splittedData[3]);
                Pageable pageable = PageRequest.of(page, ITEMS_PER_PAGE, Sort.by("name").ascending());
                Page<Step> steps = new PageImpl<>(new ArrayList<>());
                String answer = null;

                if (msgText.startsWith("author:")) {
                    var author = msgText.replaceAll("author: ", "");
                    steps = service.receiveSteps(null, null, null, author, null, pageable);
                    answer = createAnswer(author, "Степы по автору", steps);
                } else if (msgText.startsWith("step:")) {
                    var step = msgText.replaceAll("step: ", "");
                    steps = service.receiveSteps(step, null, null, null, null, pageable);
                    answer = createAnswer(step, "Степы по названию", steps);
                } else if (msgText.equals("allSteps")) {
                    steps = service.receiveSteps(null, null, null, null, null, pageable);
                    answer = createAnswer(null, "Все степы", steps);
                }
                editPage(chatId, messageId, answer, false, page, steps.getTotalPages(), msgText);
            }
        }
    }

    public String createAnswer(String value, String header, Page<Step> steps) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates/step.mustache");
        StringWriter writer = new StringWriter();

        try {
            Map<String, Object> context = new HashMap<>();
            context.put("header", header);
            if(value != null)
                context.put("value", value);
            context.put("steps", steps.getContent());
            mustache.execute(writer, context).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public void sendText(Long userId, String message, boolean parseMode) {
        SendMessage sm = SendMessage.builder()
                .chatId(userId.toString())
                .text(message)
                .build();
        sm.enableMarkdown(parseMode);

        try {
            execute(sm);
        } catch (TelegramApiException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void sendPage(Long userId, String message, boolean parseMode, int totalElements, Integer messageId, String text) {
        SendMessage sm = SendMessage.builder()
                .chatId(userId.toString())
                .text(message)
                .replyMarkup(createPaginationKeyboard(0, totalElements, messageId, text))
                .build();
        sm.enableMarkdown(parseMode);
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void editPage(Long userId, Integer messageId, String message, boolean parseMode, int page, int totalElements, String text) {
        EditMessageText em = EditMessageText.builder()
                .chatId(userId.toString())
                .messageId(messageId)
                .text(message)
                .replyMarkup(createPaginationKeyboard(page, totalElements, messageId, text))
                .build();
        em.enableMarkdown(parseMode);
        try {
            execute(em);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardMarkup createPaginationKeyboard(int currentPage, int totalPages, Integer messageId, String text) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> paginationRow = new ArrayList<>();

        // Кнопка "Назад"
        if (currentPage > 0) {
            InlineKeyboardButton prevButton = new InlineKeyboardButton();
            prevButton.setText("◀️ Назад");
            prevButton.setCallbackData("page_" + messageId + "_" + text + "_" + (currentPage - 1));
            paginationRow.add(prevButton);
        }

        // Информация о текущей странице
        InlineKeyboardButton pageInfo = new InlineKeyboardButton();
        pageInfo.setText((currentPage + 1) + "/" + totalPages);
        pageInfo.setCallbackData("page_" + currentPage);
        paginationRow.add(pageInfo);

        // Кнопка "Вперед"
        if (currentPage < totalPages - 1) {
            InlineKeyboardButton nextButton = new InlineKeyboardButton();
            nextButton.setText("Вперед ▶️");
            nextButton.setCallbackData("page_" + messageId + "_" + text + "_" + (currentPage + 1));
            paginationRow.add(nextButton);
        }

        rows.add(paginationRow);
        markup.setKeyboard(rows);
        return markup;
    }
}
