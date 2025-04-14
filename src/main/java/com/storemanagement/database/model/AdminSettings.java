package com.storemanagement.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_settings")
public class AdminSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    public AdminSettings(String password) {
        this.password = password;
    }

    public AdminSettings() {
    }
}
