package com.storemanagement.service;

import com.storemanagement.database.dto.PurchaseCreateDTO;
import com.storemanagement.database.dto.PurchasePageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    boolean create(PurchaseCreateDTO purchaseCreateDTO);

    Page<PurchasePageDTO> getAll(Pageable pageable);
}
