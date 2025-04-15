package com.storemanagement.service.impl;

import com.storemanagement.database.dto.ProductAddDTO;
import com.storemanagement.database.dto.ProductPageDTO;
import com.storemanagement.database.model.Product;
import com.storemanagement.database.repository.ProductRepository;
import com.storemanagement.service.ProductService;
import com.storemanagement.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ReportService reportService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper,
                              ReportService reportService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.reportService = reportService;
    }

    @Override
    public boolean addOrUpdateProduct(ProductAddDTO productAddDTO) {
        Optional<Product> optional = this.productRepository.findByName(productAddDTO.getName());
        Product product;

        product = optional.orElseGet(Product::new);
        setFields(productAddDTO, product);

        this.productRepository.save(product);
        this.reportService.createReport(product);
        return true;
    }

    @Override
    public List<ProductPageDTO> getAllSelected() {
        return this.productRepository.findAllBySelectedIs(true)
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

    @Override
    public boolean unselectProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setSelected(false);
                    productRepository.save(product);
                    return true;
                })
                .orElse(false);
    }

    private static void setFields(ProductAddDTO productAddDTO, Product product) {
        if (product.getName() == null) {
            product.setName(productAddDTO.getName());
        }
        product.setQuantity(productAddDTO.getQuantity());
        product.setPrice(productAddDTO.getPrice());
        product.setUnit(productAddDTO.getUnit());

        if (product.getImage() == null) {
            product.setImage(productAddDTO.getImage());
        }

        if (product.getType() == null) {
            product.setType(productAddDTO.getType());
        }

        product.setLastUpdated(LocalDateTime.now(ZoneId.of("Europe/Sofia")));
        product.setSelected(true);
        product.setSynced(false);
    }

}
