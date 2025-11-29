package ru.baskaeva.dancehallsteps.bot;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.baskaeva.dancehallsteps.model.Step;
import ru.baskaeva.dancehallsteps.properties.BotProperties;
import ru.baskaeva.dancehallsteps.services.StepService;
import ru.baskaeva.dancehallsteps.tools.MyFileReader;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {
    private final StepService service;
    private final BotProperties botProperties;
    private boolean start = false;

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
        var msg = update.getMessage();
        var user = msg.getFrom();
        var text = msg.getText();

        if(msg.isCommand()) {
            if(text.equals("/start"))
                start = true;
        }

        if(start){
            var path = "static/start";
            sendText(user.getId(), MyFileReader.readFile(path), true);
        }
        else {
            Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
            Page<Step> steps;
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile("templates/step.mustache");
            StringWriter writer = new StringWriter();
            if (text.startsWith("author:")){
                var author = text.replaceAll("author: ", "");
                steps = service.receiveSteps(null, null, null, author, null, pageable);
                if (steps.isEmpty()) {
                    sendText(user.getId(), "Степы по автору " + author + " не найдены.", false);
                    return;
                }
                try {
                    Map<String, Object> context = new HashMap<>();
                    context.put("header", "Степы по автору");
                    context.put("value", author);
                    context.put("steps", steps.getContent());
                    mustache.execute(writer, context).flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendText(user.getId(), writer.toString(), false);
            }
            else if(text.startsWith("step:")){
                var step = text.replaceAll("step: ", "");
                steps = service.receiveSteps(step, null, null, null, null, pageable);
                if (steps.isEmpty()) {
                    sendText(user.getId(), "Степы по названию " + text.replaceAll("step: ", "") + " не найдены.", false);
                    return;
                }
                try {
                    Map<String, Object> context = new HashMap<>();
                    context.put("header", "Степы по названию");
                    context.put("value", step);
                    context.put("steps", steps.getContent());
                    mustache.execute(writer, context).flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                sendText(user.getId(), writer.toString(), false);
            }
            else if(text.equals("allSteps")){
                steps = service.receiveSteps(null, null, null, null, null, pageable);
                try {
                    Map<String, Object> context = new HashMap<>();
                    context.put("header", "Все степы");
                    context.put("steps", steps.getContent());
                    mustache.execute(writer, context).flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendText(user.getId(), writer.toString(), false);
            }
            else
                sendText(user.getId(), "Не понял...", false);
        }

    }

    public void sendText(Long user, String message, boolean parseMode) {
        SendMessage sm = SendMessage.builder()
                .chatId(user.toString())
                .text(message)
                .build();
        sm.enableMarkdown(parseMode);

        try {
            execute(sm);
        } catch (TelegramApiException ex) {
            throw new RuntimeException(ex);
        }
    }
}
