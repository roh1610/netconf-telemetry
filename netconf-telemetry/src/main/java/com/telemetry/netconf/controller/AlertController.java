
package com.telemetry.netconf.controller;

import com.telemetry.netconf.model.Alert;
import com.telemetry.netconf.service.AlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // Get all alerts
    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    // Get alerts by severity (CRITICAL / WARNING)
    @GetMapping("/severity/{level}")
    public List<Alert> getAlertsBySeverity(@PathVariable String level) {
        return alertService.getAllAlerts()
                .stream()
                .filter(alert -> alert.getSeverity().equalsIgnoreCase(level))
                .toList();
    }
}
