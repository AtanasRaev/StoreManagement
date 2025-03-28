package com.storemanagement.service;

import com.storemanagement.database.dto.ProductAddDTO;
import com.storemanagement.database.dto.ProductPageDTO;

import java.util.List;

public interface ProductService {
    boolean addProduct(ProductAddDTO productAddDTO);

    List<ProductPageDTO> getAll();

    ProductPageDTO getById(Long id);
}
