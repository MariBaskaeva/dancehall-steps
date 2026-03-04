package ru.baskaeva.steps.routing;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.baskaeva.steps.annotations.BotController;
import ru.baskaeva.steps.annotations.Callback;
import ru.baskaeva.steps.annotations.Command;
import ru.baskaeva.steps.annotations.Message;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class Dispatcher {
    private final ApplicationContext ctx;

    private final Map<String, Handler> commands = new HashMap<>();
    private final List<MessageHandler> messages = new ArrayList<>();
    private final List<CallbackHandler> callbacks = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, Object> controllers = ctx.getBeansWithAnnotation(BotController.class);

        for (Object bean : controllers.values()) {
            scanController(bean);
        }

        messages.sort(Comparator.comparingInt(MessageHandler::order));
    }

    private void scanController(Object bean) {
        Class<?> type = bean.getClass();

        for (Method m : type.getMethods()) {
            Command cmd = m.getAnnotation(Command.class);
            if (cmd != null) {
                validateSignature(m);
                putUnique(commands, cmd.value(), new Handler(bean, m), "@Command");
            }

            Message msg = m.getAnnotation(Message.class);
            if (msg != null) {
                validateSignature(m);
                messages.add(new MessageHandler(Pattern.compile(msg.value()), msg.order(), new Handler(bean, m)));
            }

            Callback cb = m.getAnnotation(Callback.class);
            if (cb != null) {
                validateSignature(m);
                callbacks.add(new CallbackHandler(cb.prefix(), new Handler(bean, m)));
            }
        }
    }

    private void putUnique(Map<String, Handler> map, String key, Handler handler, String annName) {
        if (map.containsKey(key)) {
            throw new IllegalStateException(annName + " duplicate mapping for key: " + key);
        }
        map.put(key, handler);
    }

    private void validateSignature(Method m) {
        int pc = m.getParameterCount();
        if (pc == 0) return;
        if (pc == 1 && m.getParameterTypes()[0].equals(BotRequest.class)) return;
        throw new IllegalStateException("Unsupported handler signature: " + m);
    }

    public Optional<BotResponse> dispatch(BotRequest req) {
        try {
            if (req.hasCallback()) {
                String data = req.callback();
                for (CallbackHandler h : callbacks) {
                    if (data.startsWith(h.prefix())) {
                        return Optional.of(invoke(h.handler(), req));
                    }
                }
                return Optional.empty();
            }

            if (req.hasText() && req.update().getMessage().isCommand()) {
                String cmd = req.text().trim().split("\\s+")[0];
                Handler h = commands.get(cmd);
                if (h != null) return Optional.of(invoke(h, req));
                return Optional.empty();
            }

            if (req.hasText()) {
                String text = req.text();
                for (MessageHandler h : messages) {
                    if (h.pattern().matcher(text).matches()) {
                        return Optional.of(invoke(h.handler(), req));
                    }
                }
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Failed to dispatch update", e);
        }
    }

    private BotResponse invoke(Handler h, BotRequest req) throws Exception {
        if (h.method().getParameterCount() == 0) {
            return (BotResponse) h.method().invoke(h.bean());
        }
        return (BotResponse) h.method().invoke(h.bean(), req);
    }

    private record Handler(Object bean, Method method) {}
    private record MessageHandler(Pattern pattern, int order, Handler handler) {}
    private record CallbackHandler(String prefix, Handler handler) {}
}
