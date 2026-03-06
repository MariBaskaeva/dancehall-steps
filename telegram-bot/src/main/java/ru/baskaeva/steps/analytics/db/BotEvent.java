package ru.baskaeva.steps.analytics.db;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Table(name = "bot_event")
public class BotEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="event_ts", nullable=false)
    private OffsetDateTime eventTs;

    @Column(name="event_name", nullable=false)
    private String eventName;

    @Column(name="distinct_id", nullable=false)
    private String distinctId;

    @Column(name="ui_version", nullable=false)
    private String uiVersion;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name="props", columnDefinition = "jsonb")
    private Map<String, Object> props;

    @PrePersist
    public void prePersist() {
        if (eventTs == null) eventTs = OffsetDateTime.now();
    }

    public Long getId() { return id; }
    public OffsetDateTime getEventTs() { return eventTs; }
    public void setEventTs(OffsetDateTime eventTs) { this.eventTs = eventTs; }
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public String getDistinctId() { return distinctId; }
    public void setDistinctId(String distinctId) { this.distinctId = distinctId; }
    public String getUiVersion() { return uiVersion; }
    public void setUiVersion(String uiVersion) { this.uiVersion = uiVersion; }
    public Map<String, Object> getProps() { return props; }
    public void setProps(Map<String, Object> props) { this.props = props; }
}