package com.storemanagement.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal  price;

    @Column(nullable = false)
    private String unit;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "is_synced", nullable = false)
    private boolean isSynced = false;

    public Product(String name, BigDecimal quantity, BigDecimal price, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.unit = unit;
    }

    public Product() {
    }
}
