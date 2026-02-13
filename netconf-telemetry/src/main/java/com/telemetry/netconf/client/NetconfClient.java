package com.telemetry.netconf.client;

import com.telemetry.netconf.model.TelemetryData;
import com.telemetry.netconf.parser.TelemetryParser;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class NetconfClient {

    private final TelemetryParser telemetryParser;

    private boolean connected = false;

    // Simulated devices
    private final String[] devices = {
            "Firewall1", "Router1", "Switch2", "Switch4", "AccessPoint1"
    };

    public NetconfClient(TelemetryParser telemetryParser) {
        this.telemetryParser = telemetryParser;
    }

    public boolean connect(String host, int port, String user, String pass) {
        this.connected = true;
        return true;
    }

    public List<TelemetryData> getNetworkStatus() {

        if (!connected) return new ArrayList<>();

        List<TelemetryData> networkData = new ArrayList<>();
        String time = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        for (String device : devices) {

            int cpu = ThreadLocalRandom.current().nextInt(10, 95);
            int mem = ThreadLocalRandom.current().nextInt(20, 90);

            // Generate simulated XML
            String xml = generateXml(device, cpu, mem, time);

            // Parse XML into TelemetryData
            TelemetryData parsedData = telemetryParser.parse(xml);

            networkData.add(parsedData);
        }

        return networkData;
    }

    private String generateXml(String device, int cpu, int memory, String timestamp) {

        return "<notification>" +
                "<device>" + device + "</device>" +
                "<cpu>" + cpu + "</cpu>" +
                "<memory>" + memory + "</memory>" +
                "<timestamp>" + timestamp + "</timestamp>" +
                "</notification>";
    }
}