package com.storemanagement.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportCloudDTO {
    private Long id;

    private ProductCloudDTO product;

    private BigDecimal quantity;

    private LocalDateTime createdAt;
}
