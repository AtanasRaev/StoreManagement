package com.storemanagement.service;

import com.storemanagement.database.dto.PasswordDTO;

public interface AdminSettingsService {
    boolean login(PasswordDTO dto);
}
