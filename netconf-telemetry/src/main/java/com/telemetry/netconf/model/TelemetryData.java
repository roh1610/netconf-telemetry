package com.telemetry.netconf.model;

public class TelemetryData {
    private String deviceName;
    private int cpuUsage;
    private int memoryUsage;
    private String timestamp;

    public TelemetryData(String deviceName, int cpuUsage, int memoryUsage, String timestamp) {
        this.deviceName = deviceName;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.timestamp = timestamp;
    }

    public String getDeviceName() { return deviceName; }
    public int getCpuUsage() { return cpuUsage; }
    public int getMemoryUsage() { return memoryUsage; }
    public String getTimestamp() { return timestamp; }
}