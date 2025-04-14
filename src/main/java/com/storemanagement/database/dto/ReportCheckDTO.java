package com.storemanagement.database.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportCheckDTO {
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Europe/Sofia")
    private LocalDateTime loadedAt;

    private BigDecimal quantityLoaded;

    private BigDecimal quantityNow;

    private BigDecimal quantitySold;

    private BigDecimal scrap;

    private String product;

    private String image;

    private String unit;

    public ReportCheckDTO(LocalDateTime loadedAt,
                          BigDecimal quantityLoaded,
                          BigDecimal quantityNow,
                          String product,
                          String unit,
                          String image) {
        this.loadedAt = loadedAt;
        this.quantityLoaded = quantityLoaded;
        this.quantityNow = quantityNow;
        this.product = product;
        this.unit = unit;
        this.image = image;
    }
}
