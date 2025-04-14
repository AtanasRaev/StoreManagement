package com.storemanagement.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportCheckDTO {
    private Long id;

    private LocalDateTime loadedAt;

    private BigDecimal quantityLoaded;

    private BigDecimal quantityNow;

    private BigDecimal scrap;

    private String product;

    public ReportCheckDTO(LocalDateTime loadedAt,
                          BigDecimal quantityLoaded,
                          BigDecimal quantityNow,
                          String product) {
        this.loadedAt = loadedAt;
        this.quantityLoaded = quantityLoaded;
        this.quantityNow = quantityNow;
        this.product = product;
    }

}
