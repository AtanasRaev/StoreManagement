package com.storemanagement.database.repository;

import com.storemanagement.database.dto.PurchasePageDTO;
import com.storemanagement.database.dto.PurchaseQuantityDTO;
import com.storemanagement.database.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT new com.storemanagement.database.dto.PurchaseQuantityDTO(p.product.name, SUM(p.quantity)) " +
            "FROM Purchase p " +
            "WHERE p.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY p.product.name")
    List<PurchaseQuantityDTO> findPurchaseInPeriod(@Param("startDate") LocalDateTime startDate,
                                                   @Param("endDate") LocalDateTime endDate);

}
