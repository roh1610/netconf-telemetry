package com.telemetry.netconf.controller;

import com.telemetry.netconf.model.Admin;
import com.telemetry.netconf.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Admin request) {

        boolean success = service.login(
                request.getUsername(),
                request.getPassword()
        );

        Map<String, String> response = new HashMap<>();
        response.put("status", success ? "SUCCESS" : "FAILED");

        return response;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Admin request) {

        String result = service.register(
                request.getUsername(),
                request.getPassword()
        );

        Map<String, String> response = new HashMap<>();
        response.put("status", result);

        return response;
    }
}