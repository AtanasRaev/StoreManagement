package com.storemanagement.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseQuantityDTO {
    private String product;

    private BigDecimal quantity;

    public PurchaseQuantityDTO(String product, BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
