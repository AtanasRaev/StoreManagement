package com.storemanagement.controller;

import com.storemanagement.database.dto.ReportCheckDTO;
import com.storemanagement.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    public ResponseEntity<?> getReport(@RequestParam List<Long> id,
                                       @RequestParam LocalDate startDate) {
        List<ReportCheckDTO> report = this.reportService.getReportFor(id, startDate);

        return ResponseEntity.ok(Map.of(
                "report", report
        ));
    }
}
