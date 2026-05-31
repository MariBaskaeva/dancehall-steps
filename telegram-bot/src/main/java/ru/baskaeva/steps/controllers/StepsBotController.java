package ru.baskaeva.steps.controllers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import ru.baskaeva.steps.annotations.BotController;
import ru.baskaeva.steps.annotations.Callback;
import ru.baskaeva.steps.annotations.Command;
import ru.baskaeva.steps.annotations.Message;
import ru.baskaeva.steps.dto.AuthorDTO;
import ru.baskaeva.steps.dto.Era;
import ru.baskaeva.steps.routing.BotRequest;
import ru.baskaeva.steps.routing.BotResponse;
import ru.baskaeva.steps.dto.StepDTO;
import ru.baskaeva.steps.dto.StepFilter;
import ru.baskaeva.steps.properties.BotProperties;
import ru.baskaeva.steps.services.AuthorService;
import ru.baskaeva.steps.services.StepService;
import ru.baskaeva.steps.state.BotInputState;
import ru.baskaeva.steps.state.BotInputStateService;
import ru.baskaeva.steps.state.StepFilterState;
import ru.baskaeva.steps.state.StepFilterStateService;
import ru.baskaeva.steps.ui.AuthorsKeyboardFactory;
import ru.baskaeva.steps.ui.EraKeyboardFactory;
import ru.baskaeva.steps.ui.MenuKeyboardFactory;
import ru.baskaeva.steps.ui.PaginationKeyboardFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@BotController
@RequiredArgsConstructor
public class StepsBotController {

    private final StepFilterStateService stepFilterStateService;
    private final BotInputStateService inputStateService;
    private final StepService stepService;
    private final AuthorService authorService;
    private final BotProperties botProperties;
    private final PaginationKeyboardFactory paginationKeyboardFactory;
    private final MenuKeyboardFactory menuKeyboardFactory;
    private final AuthorsKeyboardFactory authorsKeyboardFactory;
    private final EraKeyboardFactory eraKeyboardFactory;


    private static final int ITEMS_PER_PAGE = 3;

    @Command("/start")
    public BotResponse start(BotRequest req) {
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

    @Command("/menu")
    public BotResponse menu(BotRequest req) {
        StepFilterState filter = stepFilterStateService.getOrCreate(req.chatId());

        SendMessage sm = SendMessage.builder()
                .chatId(req.chatId().toString())
                .text(filter.buildFilterText() + "\n\n Настройка фильтра.\n Выберите категорию для создания фильтра. Для получения результата нажмите \"Показать степы\". Вы можете сбросить полностью фильтр, нажав на соответствующую кнопку.")
                .replyMarkup(menuKeyboardFactory.create())
                .build();

        return new BotResponse(sm);
    }

    @Callback(prefix = "author_filter")
    public BotResponse authorFilter(BotRequest req) {
        Pageable pageable = PageRequest.of(0, 50, Sort.by("name").ascending());
        Page<AuthorDTO> authors = authorService.findAll(pageable);
        StepFilterState filter = stepFilterStateService.getOrCreate(req.chatId());

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text(filter.buildFilterText() + "\n\nВыберите авторов.\n Вы можете сбросить выбранных авторов, нажав кнопку \"сбросить\".")
                .replyMarkup(authorsKeyboardFactory.create(0, authors, req.messageId(), "author", filter.getAuthors()))
                .build();

        return new BotResponse(em);
    }

    @Callback(prefix = "era_filter")
    public BotResponse eraFilter(BotRequest req) {
        StepFilterState filter = stepFilterStateService.getOrCreate(req.chatId());

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text(filter.buildFilterText() + "\n\nВыберите эры.\n  Вы можете сбросить выбранные эры, нажав кнопку \"сбросить\".")
                .replyMarkup(eraKeyboardFactory.create(0, Era.values(), req.messageId(), "era", filter.getEras()))
                .build();

        return new BotResponse(em);
    }

    @Callback(prefix = "back_to_menu_btn")
    public BotResponse backToMenu(BotRequest req) {
        StepFilterState filter = stepFilterStateService.getOrCreate(req.chatId());

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text(filter.buildFilterText() + "\n\n Настройка фильтра.\n Выберите категорию для создания фильтра. Для получения результата нажмите \"Показать степы\". Вы можете сбросить полностью фильтр, нажав на соответствующую кнопку.")
                .replyMarkup(menuKeyboardFactory.create())
                .build();

        return new BotResponse(em);
    }

    @Callback(prefix = "filter:author:")
    public BotResponse selectAuthor(BotRequest req) {
        Long chatId = req.chatId();

        String[] parts = req.callback().split(":");

        String author = parts[2];
        int currentPage = Integer.parseInt(parts[3]);

        StepFilterState filter = stepFilterStateService.getOrCreate(chatId);

        if (filter.getAuthors().contains(author)) {
            filter.getAuthors().remove(author);
        } else {
            filter.getAuthors().add(author);
        }

        stepFilterStateService.save(chatId, filter);

        Pageable pageable = PageRequest.of(currentPage, 50, Sort.by("name").ascending());
        Page<AuthorDTO> authors = authorService.findAll(pageable);

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text(filter.buildFilterText() + "\n\nВыберите авторов.\n Вы можете сбросить выбранных авторов, нажав кнопку \"сбросить\".")
                .replyMarkup(authorsKeyboardFactory.create(currentPage, authors, req.messageId(), "author", filter.getAuthors()))
                .build();

        return new BotResponse(em);
    }

    @Callback(prefix = "filter:era:")
    public BotResponse selectEra(BotRequest req) {
        Long chatId = req.chatId();

        String[] parts = req.callback().split(":");

        Era era = Era.valueOf(parts[2]);
        int currentPage = Integer.parseInt(parts[3]);

        StepFilterState filter = stepFilterStateService.getOrCreate(chatId);

        if (filter.getEras().contains(era)) {
            filter.getEras().remove(era);
        } else {
            filter.getEras().add(era);
        }

        stepFilterStateService.save(chatId, filter);

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text(filter.buildFilterText() + "\n\nВыберите эры.\n  Вы можете сбросить выбранные эры, нажав кнопку \"сбросить\".")
                .replyMarkup(eraKeyboardFactory.create(currentPage, Era.values(), req.messageId(), "eras", filter.getEras()))
                .build();

        return new BotResponse(em);
    }

    @Callback(prefix = "reset:")
    public BotResponse resetFilter(BotRequest req) {
        Long chatId = req.chatId();

        String[] parts = req.callback().split(":");
        String target = parts[1];

        StepFilterState filter = stepFilterStateService.getOrCreate(chatId);

        if ("authors".equals(target)) {
            filter.getAuthors().clear();
            stepFilterStateService.save(chatId, filter);

            Pageable pageable = PageRequest.of(0, 50, Sort.by("name").ascending());
            Page<AuthorDTO> authors = authorService.findAll(pageable);

            EditMessageText em = EditMessageText.builder()
                    .chatId(chatId.toString())
                    .messageId(req.messageId())
                    .text(filter.buildFilterText() + "\n\nВыберите авторов.\n Вы можете сбросить выбранных авторов, нажав кнопку \"сбросить\".")
                    .replyMarkup(authorsKeyboardFactory.create(
                            0,
                            authors,
                            req.messageId(),
                            "author",
                            filter.getAuthors()
                    ))
                    .build();

            return new BotResponse(em);
        }

        if ("eras".equals(target)) {
            filter.getEras().clear();
            stepFilterStateService.save(chatId, filter);

            EditMessageText em = EditMessageText.builder()
                    .chatId(chatId.toString())
                    .messageId(req.messageId())
                    .text(filter.buildFilterText() + "\n\nВыберите эры.\n  Вы можете сбросить выбранные эры, нажав кнопку \"сбросить\".")
                    .replyMarkup(eraKeyboardFactory.create(
                            0,
                            Era.values(),
                            req.messageId(),
                            "author",
                            filter.getEras()
                    ))
                    .build();

            return new BotResponse(em);
        }

        if ("all".equals(target)) {
            stepFilterStateService.clear(chatId);

            filter = stepFilterStateService.getOrCreate(chatId);

            EditMessageText em = EditMessageText.builder()
                    .chatId(chatId.toString())
                    .messageId(req.messageId())
                    .text(filter.buildFilterText() + "\n\n Настройка фильтра.\n Выберите категорию для создания фильтра. Для получения результата нажмите \"Показать степы\". Вы можете сбросить полностью фильтр, нажав на соответствующую кнопку.")
                    .replyMarkup(menuKeyboardFactory.create())
                    .build();

            return new BotResponse(em);
        }

        throw new IllegalArgumentException("Unknown reset target: " + target);
    }

    @Callback(prefix = "filter:names")
    public BotResponse askStepNames(BotRequest req) {
        inputStateService.setState(req.chatId(), BotInputState.WAITING_STEP_NAMES);

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text("""
                    Введите одно или несколько названий через запятую.

                    Например:
                    back&forth, close up, non stop
                    """)
                .build();

        return new BotResponse(em);
    }

    @Callback(prefix = "showsteps_btn")
    public BotResponse showSteps(BotRequest req) {
        Pageable pageable = PageRequest.of(0, ITEMS_PER_PAGE, Sort.by("name").ascending());
        var filterState = stepFilterStateService.getOrCreate(req.chatId());
        var filter = StepFilter.builder()
                .names(filterState.getNames())
                .author(filterState.getAuthors())
                .era(filterState.getEras())
                .build();
        log.info("filters: {}", filter);
        Page<StepDTO> steps = stepService.findAll(filter, pageable);

        System.out.println("FILTER = " + filterState);
        System.out.println("TOTAL = " + steps.getTotalElements());
        System.out.println("CONTENT = " + steps.getContent());

        String answer = createAnswer(null, "Все степы", steps, filter);

        SendMessage sm = SendMessage.builder()
                .chatId(req.chatId().toString())
                .text(answer)
                .replyMarkup(paginationKeyboardFactory.create(0, steps.getTotalPages(), req.messageId(), "steps"))
                .build();

        return new BotResponse(sm);
    }

    @Callback(prefix = "page_")
    public BotResponse paginate(BotRequest req) {
        String[] parts = req.callback().split("_");

        String text = parts[2];
        int page = Integer.parseInt(parts[3]);

        Pageable pageable = PageRequest.of(page, ITEMS_PER_PAGE, Sort.by("name").ascending());

        StepFilterState filterState = stepFilterStateService.getOrCreate(req.chatId());
        var filter = StepFilter.builder()
                .names(filterState.getNames())
                .author(filterState.getAuthors())
                .era(filterState.getEras())
                .build();

        log.info("filters: {}", filter);
        Page<StepDTO> steps = stepService.findAll(filter, pageable);

        String answer = createAnswer(null, "Все степы", steps, filter);

        EditMessageText em = EditMessageText.builder()
                .chatId(req.chatId().toString())
                .messageId(req.messageId())
                .text(answer)
                .replyMarkup(paginationKeyboardFactory.create(
                        page,
                        steps.getTotalPages(),
                        req.messageId(),
                        text
                ))
                .build();

        return new BotResponse(em);
    }

    @Message(value = ".*", order = 999)
    public BotResponse fallback(BotRequest req) {
        Long chatId = req.chatId();

        BotInputState state = inputStateService.getState(chatId);

        if (state == BotInputState.WAITING_STEP_NAMES) {
            return handleStepNamesInput(req);
        }

        return new BotResponse(SendMessage.builder()
                .chatId(chatId.toString())
                .text("Не понял...")
                .build());
    }

    private BotResponse handleStepNamesInput(BotRequest req) {
        Long chatId = req.chatId();

        List<String> names = Arrays.stream(req.text().split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();

        StepFilterState filterState = stepFilterStateService.getOrCreate(chatId);
        filterState.setNames(names);
        stepFilterStateService.save(chatId, filterState);

        inputStateService.clearState(chatId);

        return new BotResponse(SendMessage.builder()
                .chatId(chatId.toString())
                .text(filterState.buildFilterText() + "\n\nНазвания сохранены.")
                .replyMarkup(menuKeyboardFactory.create())
                .build());
    }

    private String createAnswer(String value, String header, Page<StepDTO> steps, StepFilter filter) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates/step.mustache");
        StringWriter writer = new StringWriter();

        try {
            Map<String, Object> ctx = new HashMap<>();
            ctx.put("header", header);
            ctx.put("filter", filter);
            if (value != null) ctx.put("value", value);
            ctx.put("steps", steps.getContent());
            mustache.execute(writer, ctx).flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }
}