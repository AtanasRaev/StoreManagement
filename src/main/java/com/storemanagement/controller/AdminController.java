package com.storemanagement.controller;

import com.storemanagement.database.dto.PasswordDTO;
import com.storemanagement.service.AdminSettingsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminSettingsService adminSettingsService;

    public AdminController(AdminSettingsService adminSettingsService) {
        this.adminSettingsService = adminSettingsService;
    }

    @GetMapping("/has-password")
    public Map<String, Boolean> hasPassword() {
        return Map.of("hasPassword", this.adminSettingsService.hasPasswordSet());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid PasswordDTO dto) {
        if (this.adminSettingsService.hasPasswordSet()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Password already set.");
        }

        this.adminSettingsService.setPassword(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid PasswordDTO dto) {
        if (!this.adminSettingsService.login(dto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }
        return ResponseEntity.ok().build();
    }
}
