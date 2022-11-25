package com.cts.CompanyManagementApp.controller;

import com.cts.CompanyManagementApp.model.Company;
import com.cts.CompanyManagementApp.model.Stock;
import com.cts.CompanyManagementApp.service.CompanyService;
import com.cts.CompanyManagementApp.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/stock")
@Slf4j
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add/{companyCode}")
    public ResponseEntity<?> addStock(@RequestBody Stock stock, @PathVariable(name = "companyCode") long companyCode) {
        Company company = companyService.getCompanyByCode(companyCode);
        log.info(company.toString());
        if(company!=null) {
            company.setStockPrice(stock.getStockPrice());
            if(companyService.updateCompany(company) && stockService.addStock(stock)) {
                return ResponseEntity.ok().body("Company table updated and Stock table added Successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add Stock details");

    }


}
