
package com.telemetry.netconf.service;

import com.telemetry.netconf.model.Alert;
import com.telemetry.netconf.model.TelemetryData;
import com.telemetry.netconf.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private static final int CPU_THRESHOLD = 80;
    private static final int MEMORY_THRESHOLD = 75;

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void checkAndGenerateAlerts(TelemetryData data) {

        List<Alert> alerts = new ArrayList<>();

        if (data.getCpuUsage() > CPU_THRESHOLD) {
            alerts.add(new Alert(
                    data.getDeviceName(),
                    "High CPU usage detected: " + data.getCpuUsage() + "%",
                    "CRITICAL"
            ));
        }

        if (data.getMemoryUsage() > MEMORY_THRESHOLD) {
            alerts.add(new Alert(
                    data.getDeviceName(),
                    "High Memory usage detected: " + data.getMemoryUsage() + "%",
                    "WARNING"
            ));
        }

        if (!alerts.isEmpty()) {
            alertRepository.saveAll(alerts);
        }
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}
