package ru.baskaeva.steps.state;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BotInputStateService {

    private final Map<Long, BotInputState> states = new ConcurrentHashMap<>();

    public void setState(Long chatId, BotInputState state) {
        states.put(chatId, state);
    }

    public BotInputState getState(Long chatId) {
        return states.getOrDefault(chatId, BotInputState.NONE);
    }

    public void clearState(Long chatId) {
        states.remove(chatId);
    }
}