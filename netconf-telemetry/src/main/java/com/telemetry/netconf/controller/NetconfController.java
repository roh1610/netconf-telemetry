package com.telemetry.netconf.controller;

import com.telemetry.netconf.service.AlertService;

import com.telemetry.netconf.client.NetconfClient;
import com.telemetry.netconf.model.TelemetryData;
import com.telemetry.netconf.model.TelemetryRecord;
import com.telemetry.netconf.repository.TelemetryRepository;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/netconf")
public class NetconfController {

    private final NetconfClient netconfClient;
    private final TelemetryRepository repository;
    private final AlertService alertService;

    public NetconfController(NetconfClient netconfClient,
                             TelemetryRepository repository,
                             AlertService alertService) {
        this.netconfClient = netconfClient;
        this.repository = repository;
        this.alertService = alertService;
    }

    @PostMapping("/connect")
    public Map<String, String> connect() {
         boolean success = netconfClient.connect("192.168.1.100", 830, "admin", "password");
         Map<String, String> response = new HashMap<>();
         response.put("status", success ? "CONNECTED" : "FAILED");
         return response;
    }

    @GetMapping("/dashboard-data")
    public List<TelemetryData> getDashboardData() {
        List<TelemetryData> dataList = netconfClient.getNetworkStatus();

        // Save every record to the database
        for(TelemetryData d : dataList) {
            repository.save(new TelemetryRecord(d.getDeviceName(), d.getCpuUsage(), d.getMemoryUsage()));
            alertService.checkAndGenerateAlerts(d);
        }
        
        return dataList;
    }
}