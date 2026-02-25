package ru.baskaeva.steps.properties;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class BotProperties {
    private String token;
    private String name;
    private Resource greeting;
}
