package ru.baskaeva.steps.controllers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import ru.baskaeva.steps.annotations.BotController;
import ru.baskaeva.steps.annotations.Callback;
import ru.baskaeva.steps.annotations.Command;
import ru.baskaeva.steps.annotations.Message;
import ru.baskaeva.steps.routing.BotRequest;
import ru.baskaeva.steps.routing.BotResponse;
import ru.baskaeva.steps.dto.StepDTO;
import ru.baskaeva.steps.dto.StepFilter;
import ru.baskaeva.steps.properties.BotProperties;
import ru.baskaeva.steps.services.StepService;
import ru.baskaeva.steps.ui.PaginationKeyboardFactory;
import ru.baskaeva.steps.analytics.AnalyticsEventName;
import ru.baskaeva.steps.analytics.AnalyticsService;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;


@BotController
@RequiredArgsConstructor
public class StepsBotController {

    private final StepService service;
    private final BotProperties botProperties;
    private final PaginationKeyboardFactory factory;
    private final AnalyticsService analytics;

    private static final int ITEMS_PER_PAGE = 3;

    @Command("/start")
    public BotResponse start(BotRequest req) {
        analytics.track(req, AnalyticsEventName.BOT_STARTED, Map.of());

        String greeting;
        try {
            greeting = botProperties.getGreeting().getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            greeting = "Привет!";
        }

        SendMessage sm = SendMessage.builder()
                .chatId(req.chatId().toString())
                .text(greeting)
                .build();
        sm.enableMarkdown(true);

        return new BotResponse(sm);
    }

    @Message(value = "^author:.*", order = 1)
    public BotResponse byAuthor(BotRequest req) {
        String text = req.text();
        String author = text.replace("author:", "").trim();

        analytics.track(req, AnalyticsEventName.SEARCH_STARTED, Map.of("method", "author"));

        Pageable pageable = PageRequest.of(0, ITEMS_PER_PAGE, Sort.by("name").ascending());
        Page<StepDTO> steps = service.findAll(StepFilter.builder().author(author).build(), pageable);

        if (steps.isEmpty()) {
            analytics.track(req, AnalyticsEventName.NO_RESULTS, Map.of("method", "author"));

            return new BotResponse(SendMessage.builder()
                    .chatId(req.chatId().toString())
                    .text("Степы по автору " + author + " не найдены.")
                    .build());
        }

        analytics.track(req, AnalyticsEventName.RESULTS_SHOWN, Map.of(
                "method", "author",
                "results_count", steps.getTotalElements(),
                "page", 0,
                "total_pages", steps.getTotalPages()));

        String answer = createAnswer(author, "Степы по автору", steps);

        SendMessage sm = SendMessage.builder()
                .chatId(req.chatId().toString())
                .text(answer)
                .replyMarkup(factory.create(0, steps.getTotalPages(), req.messageId(), text))
                .build();

        return new BotResponse(sm);
    }

    @Message(value = "^step:.*", order = 1)
    public BotResponse byStep(BotRequest req) {
        String text = req.text();
        String name = text.replace("step:", "").trim();

        analytics.track(req, AnalyticsEventName.SEARCH_STARTED, Map.of("method", "step"));

        Pageable pageable = PageRequest.of(0, ITEMS_PER_PAGE, Sort.by("name").ascending());
        Page<StepDTO> steps = service.findAll(StepFilter.builder().name(name).build(), pageable);

        if (steps.isEmpty()) {
            analytics.track(req, AnalyticsEventName.NO_RESULTS, Map.of("method", "step"));

            return new BotResponse(SendMessage.builder()
                    .chatId(req.chatId().toString())
                    .text("Степы по названию " + name + " не найдены.")
                    .build());
        }
        analytics.track(req, AnalyticsEventName.RESULTS_SHOWN, Map.of(
                "method", "step",
                "results_count", steps.getTotalElements(),
                "page", 0,
                "total_pages", steps.getTotalPages()));

        String answer = createAnswer(name, "Степы по названию", steps);

        SendMessage sm = SendMessage.builder()
                .chatId(req.chatId().toString())
                .text(answer)
                .replyMarkup(factory.create(0, steps.getTotalPages(), req.messageId(), text))
                .build();

        return new BotResponse(sm);
    }

    @Message(value = "^allSteps$", order = 1)
    public BotResponse allSteps(BotRequest req) {
        analytics.track(req, AnalyticsEventName.SEARCH_STARTED, Map.of(
                "method", "allSteps"
        ));

        Pageable pageable = PageRequest.of(0, ITEMS_PER_PAGE, Sort.by("name").ascending());
        Page<StepDTO> steps = service.findAll(new StepFilter(), pageable);

        analytics.track(req, AnalyticsEventName.RESULTS_SHOWN, Map.of(
                "method", "allSteps",
                "results_count", steps.getTotalElements(),
                "page", 0,
                "total_pages", steps.getTotalPages()));

        String answer = createAnswer(null, "Все степы", steps);

        SendMessage sm = SendMessage.builder()
                .chatId(req.chatId().toString())
                .text(answer)
                .replyMarkup(factory.create(0, steps.getTotalPages(), req.messageId(), req.text()))
                .build();

        return new BotResponse(sm);
    }

    @Callback(prefix = "page_")
    public BotResponse paginate(BotRequest req) {
        PageRequestData data = PageRequestData.parse(req.callback());

        Pageable pageable = PageRequest.of(data.page(), ITEMS_PER_PAGE, Sort.by("name").ascending());

        Page<StepDTO> steps;
        String answer;
        String method;

        if (data.text().startsWith("author:")) {
            String author = data.text().replace("author:", "").trim();
            steps = service.findAll(StepFilter.builder().author(author).build(), pageable);
            answer = createAnswer(author, "Степы по автору", steps);
            method = "author";
        } else if (data.text().startsWith("step:")) {
            String name = data.text().replace("step:", "").trim();
            steps = service.findAll(StepFilter.builder().name(name).build(), pageable);
            answer = createAnswer(name, "Степы по названию", steps);
            method = "step";
        } else {
            steps = service.findAll(new StepFilter(), pageable);
            answer = createAnswer(null, "Все степы", steps);
            method = "allSteps";
        }
        analytics.track(req, AnalyticsEventName.RESULTS_SHOWN, Map.of(
                "method", method,
                "results_count", steps.getTotalElements(),
                "page", data.page(),
                "total_pages", steps.getTotalPages()
        ));

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text(answer)
                .replyMarkup(factory.create(data.page(), steps.getTotalPages(), data.messageId(), data.text()))
                .build();

        return new BotResponse(em);
    }

    @Message(value = ".*", order = 999)
    public BotResponse fallback(BotRequest req) {
        analytics.track(req, AnalyticsEventName.UNKNOWN_INPUT, Map.of(
                "text", req.text()
        ));

        return new BotResponse(SendMessage.builder()
                .chatId(req.chatId().toString())
                .text("Не понял...")
                .build());
    }

    private String createAnswer(String value, String header, Page<StepDTO> steps) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates/step.mustache");
        StringWriter writer = new StringWriter();

        try {
            Map<String, Object> ctx = new HashMap<>();
            ctx.put("header", header);
            if (value != null) ctx.put("value", value);
            ctx.put("steps", steps.getContent());
            mustache.execute(writer, ctx).flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }

    private record PageRequestData(int messageId, String text, int page) {
        static PageRequestData parse(String data) {
            int first = data.indexOf('_');
            int second = data.indexOf('_', first + 1);
            int last = data.lastIndexOf('_');

            int messageId = Integer.parseInt(data.substring(first + 1, second));
            String text = data.substring(second + 1, last);
            int page = Integer.parseInt(data.substring(last + 1));
            return new PageRequestData(messageId, text, page);
        }
    }
}