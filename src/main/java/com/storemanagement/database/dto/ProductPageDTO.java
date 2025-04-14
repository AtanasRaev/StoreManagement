package com.storemanagement.database.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductPageDTO {
    private long id;

    private String name;

    private BigDecimal quantity;

    private BigDecimal  price;

    private String unit;

    private String type;

    private String image;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Europe/Sofia")
    private LocalDateTime lastUpdated;
}
