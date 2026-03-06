package ru.baskaeva.steps.analytics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsEvent {

    private final AnalyticsEventName name;
    private final String distinctId;
    private final String uiVersion;
    private final Map<String, Object> props;

    private AnalyticsEvent(AnalyticsEventName name, String distinctId, String uiVersion, Map<String, Object> props) {
        this.name = name;
        this.distinctId = distinctId;
        this.uiVersion = uiVersion;
        this.props = props;
    }

    public AnalyticsEventName getName() { return name; }
    public String getDistinctId() { return distinctId; }
    public String getUiVersion() { return uiVersion; }
    public Map<String, Object> getProps() { return props; }

    public static Builder builder(AnalyticsEventName name, String distinctId, String uiVersion) {
        return new Builder(name, distinctId, uiVersion);
    }

    public static final class Builder {
        private final AnalyticsEventName name;
        private final String distinctId;
        private final String uiVersion;
        private final Map<String, Object> props = new HashMap<>();

        private Builder(AnalyticsEventName name, String distinctId, String uiVersion) {
            this.name = name;
            this.distinctId = distinctId;
            this.uiVersion = uiVersion;
        }

        public Builder prop(String key, Object value) {
            if (key != null && value != null) {
                props.put(key, value);
            }
            return this;
        }

        public Builder props(Map<String, Object> map) {
            if (map != null) {
                map.forEach(this::prop);
            }
            return this;
        }

        public AnalyticsEvent build() {
            Map<String, Object> finalProps = new HashMap<>(props);
            finalProps.put("ui_version", uiVersion);
            return new AnalyticsEvent(name, distinctId, uiVersion, Collections.unmodifiableMap(finalProps));
        }
    }
}