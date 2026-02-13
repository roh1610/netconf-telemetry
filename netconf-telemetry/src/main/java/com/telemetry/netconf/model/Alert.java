
package com.telemetry.netconf.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;

    private String message;

    private String severity; // INFO, WARNING, CRITICAL

    private LocalDateTime timestamp;

    public Alert() {
    }

    public Alert(String deviceName, String message, String severity) {
        this.deviceName = deviceName;
        this.message = message;
        this.severity = severity;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getMessage() {
        return message;
    }

    public String getSeverity() {
        return severity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
