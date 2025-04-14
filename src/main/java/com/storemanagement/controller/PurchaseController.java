package com.storemanagement.controller;

import com.storemanagement.database.dto.PurchaseCreateDTO;
import com.storemanagement.database.dto.PurchasePageDTO;
import com.storemanagement.service.PurchaseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPurchase(@RequestBody @Valid PurchaseCreateDTO purchaseCreateDTO) {
        if (!this.purchaseService.create(purchaseCreateDTO)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Purchase not created"));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Purchase created"
        ));
    }

    @GetMapping
    public ResponseEntity<?> getAllPurchases(@RequestParam(defaultValue = "createdAt") String sort,
                                             @RequestParam(defaultValue = "10") @Min(4) @Max(100) int size,
                                             @RequestParam(defaultValue = "1") @Min(1) int page) {
        Pageable pageable = getPageable(sort, size, page);
        Page<PurchasePageDTO> purchasePage = this.purchaseService.getAll(pageable);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "purchases", purchasePage.getContent(),
                "items_on_page", purchasePage.getNumberOfElements(),
                "total_items", purchasePage.getTotalElements(),
                "total_pages", purchasePage.getTotalPages(),
                "current_page", purchasePage.getNumber() + 1
        ));
    }

    private static PageRequest getPageable(String sort, int size, int page) {
        Sort.Direction direction = Sort.Direction.DESC;
        String sortBy;

        switch (sort) {
            case "priceDesc" -> sortBy = "price";
            case "oldest" -> {
                sortBy = "createdAt";
                direction = Sort.Direction.ASC;
            }
            case "priceAsc" -> {
                sortBy = "price";
                direction = Sort.Direction.ASC;
            }
            default -> sortBy = "createdAt";
        }

        return PageRequest.of(page - 1, size, Sort.by(direction, sortBy));
    }
}
