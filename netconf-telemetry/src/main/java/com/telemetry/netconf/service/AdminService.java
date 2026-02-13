package com.telemetry.netconf.service;

import com.telemetry.netconf.model.Admin;
import com.telemetry.netconf.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    // LOGIN
    public boolean login(String username, String password) {
        return repository.findByUsername(username)
                .map(admin -> admin.getPassword().equals(password))
                .orElse(false);
    }

    // REGISTER
    public String register(String username, String password) {

        if (repository.findByUsername(username).isPresent()) {
            return "EXISTS";
        }

        repository.save(new Admin(username, password));
        return "CREATED";
    }
}