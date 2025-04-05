package com.storemanagement.controller;

import com.storemanagement.database.dto.PasswordDTO;
import com.storemanagement.service.AdminSettingsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminSettingsService adminSettingsService;

    public AdminController(AdminSettingsService adminSettingsService) {
        this.adminSettingsService = adminSettingsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid PasswordDTO dto) {
        if (!this.adminSettingsService.login(dto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "message", "Invalid password")
                    );
        }
        return ResponseEntity.ok(Map.of(
                "message", "Successfully logged in"
        ));
    }
}
