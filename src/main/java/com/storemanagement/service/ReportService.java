package com.storemanagement.service;

import com.storemanagement.database.dto.ReportCheckDTO;
import com.storemanagement.database.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    void createReport(Product product);

    List<ReportCheckDTO> getReportInPeriod(LocalDate startDate);
}
