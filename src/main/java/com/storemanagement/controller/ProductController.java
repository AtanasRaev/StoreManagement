package com.storemanagement.controller;

import com.storemanagement.database.dto.ProductAddDTO;
import com.storemanagement.database.dto.ProductPageDTO;
import com.storemanagement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductAddDTO productAddDTO) {
        if (!this.productService.addOrUpdateProduct(productAddDTO)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", "Product not added"
                    ));
        }
        return ResponseEntity.ok(Map.of(
                "message", "Successfully added product"
        ));
    }

    @GetMapping
    public ResponseEntity<?> getAllSelectedProducts() {
        return ResponseEntity.ok(Map.of(
                "products", this.productService.getAllSelected()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", "Id must be greater than 0"
                    ));
        }

        ProductPageDTO productDTO = this.productService.getById(id);

        if (productDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message", "Product with id " + id + " not found"
                    ));
        }

        return ResponseEntity.ok(Map.of(
                "product", productDTO
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> unselectProduct(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", "Id must be greater than 0"
                    ));
        }

        if (!this.productService.unselectProduct(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", "Product not unselected"
                    ));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Product with id " + id + " was edited"
        ));
    }
}
