package com.storemanagement.service.impl;

import com.storemanagement.database.dto.PasswordDTO;
import com.storemanagement.database.model.AdminSettings;
import com.storemanagement.database.repository.AdminSettingsRepository;
import com.storemanagement.service.AdminSettingsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminSettingsServiceImpl implements AdminSettingsService {
    private final AdminSettingsRepository adminSettingsRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSettingsServiceImpl(AdminSettingsRepository adminSettingsRepository,
                                    PasswordEncoder passwordEncoder) {
        this.adminSettingsRepository = adminSettingsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(PasswordDTO dto) {
        Optional<AdminSettings> optional = this.adminSettingsRepository.findById(1L);

        if (optional.isEmpty()) {
            this.adminSettingsRepository.save(new AdminSettings(this.passwordEncoder.encode(dto.getPassword())));
            return true;
        }

        return optional.map(adminSettings ->
                adminSettings.getPassword().equals(this.passwordEncoder.encode(dto.getPassword()))
        ).orElse(false);
    }
}
