package com.storemanagement.service.impl;

import com.storemanagement.database.dto.PurchaseQuantityDTO;
import com.storemanagement.database.dto.ReportCheckDTO;
import com.storemanagement.database.model.Product;
import com.storemanagement.database.model.Report;
import com.storemanagement.database.repository.ReportRepository;
import com.storemanagement.service.PurchaseService;
import com.storemanagement.service.ReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final PurchaseService purchaseService;

    public ReportServiceImpl(ReportRepository reportRepository,
                             PurchaseService purchaseService) {
        this.reportRepository = reportRepository;
        this.purchaseService = purchaseService;
    }

    @Override
    public void createReport(Product product) {
        this.reportRepository.save(new Report(
                product,
                product.getQuantity(),
                LocalDateTime.now(ZoneId.of("Europe/Sofia")),
                false));
    }

    @Override
    public List<ReportCheckDTO> getReportInPeriod(LocalDate startDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Sofia"));

        List<ReportCheckDTO> reportSummaryInPeriod = reportRepository.findReportSummaryInPeriod(startDateTime, now);
        List<PurchaseQuantityDTO> purchaseQuantityInPeriod = purchaseService.getPurchaseQuantityInPeriod(startDateTime, now);

        Map<String, PurchaseQuantityDTO> purchaseMap = purchaseQuantityInPeriod.stream()
                .collect(Collectors.toMap(PurchaseQuantityDTO::getProduct, Function.identity()));

        reportSummaryInPeriod.forEach(report -> {
            if (report.getQuantityNow() == null || report.getQuantityLoaded() == null) {
                return;
            }
            PurchaseQuantityDTO purchase = purchaseMap.get(report.getProduct());
            if (purchase != null && purchase.getQuantity() != null) {
                BigDecimal combinedQuantity = report.getQuantityNow().add(purchase.getQuantity());
                if (combinedQuantity.compareTo(report.getQuantityLoaded()) != 0) {
                    BigDecimal scrap = report.getQuantityLoaded().subtract(combinedQuantity);
                    report.setScrap(scrap);
                }
            }
        });

        return reportSummaryInPeriod;
    }
}
