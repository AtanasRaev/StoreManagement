package com.storemanagement.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseCloudDTO {
    private Long id;

    private ProductCloudDTO product;

    private BigDecimal quantity;

    private BigDecimal price;

    private LocalDateTime createdAt;
}
