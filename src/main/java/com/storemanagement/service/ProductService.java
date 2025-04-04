package com.storemanagement.service;

import com.storemanagement.database.dto.ProductAddDTO;
import com.storemanagement.database.dto.ProductPageDTO;

import java.util.List;

public interface ProductService {
    boolean addOrUpdateProduct(ProductAddDTO productAddDTO);

    List<ProductPageDTO> getAllSelected();

    ProductPageDTO getById(Long id);

    boolean unselectProduct(Long id);
}
