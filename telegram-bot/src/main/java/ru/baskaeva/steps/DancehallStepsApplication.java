package ru.baskaeva.steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class DancehallStepsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DancehallStepsApplication.class, args);
    }

}
