package com.storemanagement.service.impl;

import com.storemanagement.database.dto.PurchaseCreateDTO;
import com.storemanagement.database.dto.PurchasePageDTO;
import com.storemanagement.database.model.Product;
import com.storemanagement.database.model.Purchase;
import com.storemanagement.database.repository.ProductRepository;
import com.storemanagement.database.repository.PurchaseRepository;
import com.storemanagement.service.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               ProductRepository productRepository,
                               ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public boolean create(PurchaseCreateDTO purchaseCreateDTO) {
        Optional<Product> optionalProduct = this.productRepository.findById(purchaseCreateDTO.getId());

        if (optionalProduct.isEmpty()) {
            return false;
        }
        Product product = optionalProduct.get();
        product.setQuantity(product.getQuantity().subtract(purchaseCreateDTO.getQuantity()));

        this.productRepository.save(product);

        this.purchaseRepository.save(new Purchase(
                product,
                purchaseCreateDTO.getQuantity(),
                purchaseCreateDTO.getQuantity().multiply(product.getPrice()),
                LocalDateTime.now(ZoneId.of("Europe/Sofia")),
                false
        ));
        return true;
    }

    @Override
    public Page<PurchasePageDTO> getAll(Pageable pageable) {
        return this.purchaseRepository.findAll(pageable)
                .map(purchase -> this.modelMapper.map(purchase, PurchasePageDTO.class));
    }
}
