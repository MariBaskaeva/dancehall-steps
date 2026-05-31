package ru.baskaeva.steps.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.baskaeva.steps.state.StepFilterState;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, StepFilterState> stepFilterStateRedisTemplate(
            RedisConnectionFactory connectionFactory
    ) {
        RedisTemplate<String, StepFilterState> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        Jackson2JsonRedisSerializer<StepFilterState> serializer =
                new Jackson2JsonRedisSerializer<>(StepFilterState.class);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();

        return template;
    }
}