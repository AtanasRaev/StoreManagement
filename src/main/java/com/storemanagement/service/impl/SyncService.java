package com.storemanagement.service.impl;

import com.storemanagement.database.dto.ProductCloudDTO;
import com.storemanagement.database.dto.PurchaseCloudDTO;
import com.storemanagement.database.dto.ReportCloudDTO;
import com.storemanagement.database.model.Product;
import com.storemanagement.database.model.Purchase;
import com.storemanagement.database.model.Report;
import com.storemanagement.database.repository.ProductRepository;
import com.storemanagement.database.repository.PurchaseRepository;
import com.storemanagement.database.repository.ReportRepository;
import com.storemanagement.service.InternetCheckerService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class SyncService {
    private final InternetCheckerService internetCheckerService;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final ReportRepository reportRepository;
    private final WebClient webClient;
    private final static String API_URL = "";

    public SyncService(InternetCheckerService internetCheckerService,
                       ProductRepository productRepository,
                       PurchaseRepository purchaseRepository,
                       ReportRepository reportRepository,
                       WebClient webClient) {
        this.internetCheckerService = internetCheckerService;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
        this.reportRepository = reportRepository;
        this.webClient = webClient;
    }

    @Scheduled(fixedRate = 120000)
    private void sync() {
        if (!this.internetCheckerService.isInternetAvailable()) {
            return;
        }

        Map<String, List<String>> syncProducts = syncProducts();
        List<Product> products = this.productRepository.findAllByNameIn(syncProducts.get("names"));
        products.forEach(product -> product.setSynced(true));
        this.productRepository.saveAll(products);


        Map<String, List<Long>> syncPurchases = syncPurchases();
        List<Purchase> purchases = this.purchaseRepository.findAllByIdIn(syncPurchases.get("appIds"));
        purchases.forEach(purchase -> purchase.setSynced(true));
        this.purchaseRepository.saveAll(purchases);

        Map<String, List<Long>> syncReports = syncReports();
        List<Report> reports = this.reportRepository.findAllByIdIn(syncReports.get("appIds"));
        reports.forEach(report -> report.setSynced(true));
        this.reportRepository.saveAll(reports);
    }

    private Map<String, List<String>> syncProducts() {
        List<ProductCloudDTO> notSynced = this.productRepository.findAllNotSynced();
        return this.webClient.post()
                .uri(API_URL + "/products")
                .body(BodyInserters.fromValue(notSynced))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<String>>>() {
                })
                .block();
    }


    private Map<String, List<Long>> syncPurchases() {
        List<PurchaseCloudDTO> notSynced = this.purchaseRepository.findAllNotSynced();
        return this.webClient.post()
                .uri(API_URL + "/purchases")
                .body(BodyInserters.fromValue(notSynced))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<Long>>>() {
                })
                .block();
    }

    private Map<String, List<Long>> syncReports() {
        List<ReportCloudDTO> notSynced = this.reportRepository.findAllNotSynced();
        return this.webClient.post()
                .uri(API_URL + "/reports")
                .body(BodyInserters.fromValue(notSynced))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<Long>>>() {
                })
                .block();
    }
}
