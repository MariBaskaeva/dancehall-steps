package ru.baskaeva.steps.analytics.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BotEventRepository extends JpaRepository<BotEvent, Long> {}