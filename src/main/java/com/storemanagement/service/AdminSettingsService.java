package com.storemanagement.service;

import com.storemanagement.database.dto.PasswordDTO;

public interface AdminSettingsService {
    boolean hasPasswordSet();

    void setPassword(PasswordDTO dto);

    boolean login(PasswordDTO dto);
}
