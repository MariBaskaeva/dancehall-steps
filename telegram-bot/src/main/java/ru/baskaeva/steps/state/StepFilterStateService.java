package ru.baskaeva.steps.state;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class StepFilterStateService {

    private final RedisTemplate<String, StepFilterState> redisTemplate;

    public StepFilterStateService(RedisTemplate<String, StepFilterState> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public StepFilterState getOrCreate(Long userId) {
        StepFilterState state = redisTemplate.opsForValue().get(key(userId));

        if (state == null) {
            state = new StepFilterState();
            save(userId, state);
        }

        return state;
    }

    public void save(Long userId, StepFilterState state) {
        redisTemplate.opsForValue().set(
                key(userId),
                state,
                Duration.ofDays(1)
        );
    }

    public void clear(Long userId) {
        redisTemplate.delete(key(userId));
    }

    private String key(Long userId) {
        return "filter:" + userId;
    }
}