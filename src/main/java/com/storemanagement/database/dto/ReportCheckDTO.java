package com.storemanagement.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportCheckDTO {
    private Long id;

    private LocalDateTime stockLoadedAt;

    private BigDecimal stockQuantityLoaded;

    private BigDecimal stockQuantityNow;

    private String product;

    public ReportCheckDTO(LocalDateTime stockLoadedAt,
                          BigDecimal stockQuantityLoaded,
                          BigDecimal stockQuantityNow,
                          String product) {
        this.stockLoadedAt = stockLoadedAt;
        this.stockQuantityLoaded = stockQuantityLoaded;
        this.stockQuantityNow = stockQuantityNow;
        this.product = product;
    }

}
