package com.storemanagement.service.impl;

import com.storemanagement.database.dto.ProductAddDTO;
import com.storemanagement.database.dto.ProductPageDTO;
import com.storemanagement.database.model.Product;
import com.storemanagement.database.repository.ProductRepository;
import com.storemanagement.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addProduct(ProductAddDTO productAddDTO) {
        this.productRepository.save(new Product(
                productAddDTO.getName(),
                productAddDTO.getQuantity(),
                productAddDTO.getPrice(),
                productAddDTO.getUnit()
        ));

        return true;
    }

    @Override
    public List<ProductPageDTO> getAll() {
        return this.productRepository.findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductPageDTO.class))
                .toList();
    }

    @Override
    public ProductPageDTO getById(Long id) {
        return this.productRepository.findById(id)
                .map(product -> this.modelMapper.map(product, ProductPageDTO.class))
                .orElse(null);
    }
}
