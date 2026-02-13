
package com.telemetry.netconf.parser;

import com.telemetry.netconf.model.TelemetryData;
import org.springframework.stereotype.Component;

@Component
public class TelemetryParser {

    public TelemetryData parse(String xml) {

        String device = extract(xml, "device");
        int cpu = Integer.parseInt(extract(xml, "cpu"));
        int memory = Integer.parseInt(extract(xml, "memory"));
        String timestamp = extract(xml, "timestamp");

        return new TelemetryData(device, cpu, memory, timestamp);
    }

    private String extract(String xml, String tag) {

        int start = xml.indexOf("<" + tag + ">");
        int end = xml.indexOf("</" + tag + ">");

        if (start == -1 || end == -1) {
            return "0";
        }

        start += tag.length() + 2;

        return xml.substring(start, end);
    }
}
