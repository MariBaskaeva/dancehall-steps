package ru.baskaeva.steps.routing;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public record BotResponse(BotApiMethod<?> method) {}