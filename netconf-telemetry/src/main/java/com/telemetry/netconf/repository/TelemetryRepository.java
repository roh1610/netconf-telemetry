package com.telemetry.netconf.repository;

import com.telemetry.netconf.model.TelemetryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryRecord, Long> {
}
