package com.storemanagement.service;

import com.storemanagement.database.dto.PurchaseCreateDTO;
import com.storemanagement.database.dto.PurchasePageDTO;
import com.storemanagement.database.dto.PurchaseQuantityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseService {
    boolean create(PurchaseCreateDTO purchaseCreateDTO);

    Page<PurchasePageDTO> getAll(Pageable pageable);

    List<PurchaseQuantityDTO> getPurchaseQuantityInPeriod(LocalDateTime startDate, LocalDateTime endDate);
}
