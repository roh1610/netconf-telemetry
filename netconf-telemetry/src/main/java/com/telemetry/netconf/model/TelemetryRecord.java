package com.telemetry.netconf.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry_record")
public class TelemetryRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime timestamp;
    private String deviceName;
    private int cpuUsage;
    private int memoryUsage;

    public TelemetryRecord() {}

    public TelemetryRecord(String deviceName, int cpuUsage, int memoryUsage) {
        this.timestamp = LocalDateTime.now();
        this.deviceName = deviceName;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
    }
    
    public Long getId() { return id; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDeviceName() { return deviceName; }
    public int getCpuUsage() { return cpuUsage; }
    public int getMemoryUsage() { return memoryUsage; }
}