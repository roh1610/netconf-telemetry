
package com.telemetry.netconf.repository;

import com.telemetry.netconf.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    // Find alerts by device name
    List<Alert> findByDeviceName(String deviceName);

    // Find alerts by severity (INFO, WARNING, CRITICAL)
    List<Alert> findBySeverity(String severity);
}
