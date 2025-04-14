package com.storemanagement.database.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal  price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_synced", nullable = false)
    private boolean isSynced;

    public Purchase(Product product, BigDecimal quantity, BigDecimal price, LocalDateTime createdAt, boolean isSynced) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
        this.isSynced = isSynced;
    }

    public Purchase() {
    }
}
