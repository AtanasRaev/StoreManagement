package com.storemanagement.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseQuantityDTO {
    private String product;

    private BigDecimal quantity;

    private BigDecimal total;

    public PurchaseQuantityDTO(String product,
                               BigDecimal quantity,
                               BigDecimal total) {
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }
}
