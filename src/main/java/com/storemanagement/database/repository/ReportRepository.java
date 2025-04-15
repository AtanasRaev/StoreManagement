package com.storemanagement.database.repository;

import com.storemanagement.database.dto.ReportCheckDTO;
import com.storemanagement.database.dto.ReportCloudDTO;
import com.storemanagement.database.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("""
            SELECT new com.storemanagement.database.dto.ReportCheckDTO(
                MIN(r.createdAt),
                SUM(r.quantity),
                r.product.quantity,
                r.product.name,
                r.product.unit,
                r.product.image
            )
            FROM Report r
            WHERE r.createdAt BETWEEN :startDate AND :endDate
            GROUP BY r.product.id, r.product.quantity, r.product""")
    List<ReportCheckDTO> findReportSummaryInPeriod(
            @Param("startDate") LocalDateTime start,
            @Param("endDate") LocalDateTime end
    );

    @Query("SELECT p FROM Report p WHERE p.isSynced = false")
    List<ReportCloudDTO> findAllNotSynced();

    List<Report> findAllByIdIn(List<Long> appIds);
}
