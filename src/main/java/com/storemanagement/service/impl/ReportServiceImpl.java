package com.storemanagement.service.impl;

import com.storemanagement.database.dto.ReportCheckDTO;
import com.storemanagement.database.model.Product;
import com.storemanagement.database.model.Report;
import com.storemanagement.database.repository.ReportRepository;
import com.storemanagement.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
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
    public List<ReportCheckDTO> getReportFor(List<Long> id, LocalDate startDate) {
        return this.reportRepository.findReportSummaryInPeriod(id, startDate.atStartOfDay(), LocalDateTime.now(ZoneId.of("Europe/Sofia")));
    }
}
