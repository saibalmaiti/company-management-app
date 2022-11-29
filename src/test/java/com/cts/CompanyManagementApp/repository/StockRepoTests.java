package com.cts.CompanyManagementApp.repository;

import com.cts.CompanyManagementApp.model.Company;
import com.cts.CompanyManagementApp.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureMockMvc
public class StockRepoTests {
    @Autowired
    private StockRepo stockRepo;
    @Autowired
    private CompanyRepo companyRepo;

    Stock stock = new Stock();
    Company company = new Company();
    @BeforeEach
    public void init() {
        stock.setTransactionId(1L);
        stock.setStockPrice(114.1);
        stock.setUpdatedAt(new Date());
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        List<Stock> stockList = new ArrayList<>();
        stockList.add(stock);
        company.setStockList(stockList);
    }
    @Test
    public void saveStockSuccess() throws Exception {
        Stock stock1 = null;
        stockRepo.save(stock);
        stock1 = stockRepo.findById(stock.getTransactionId()).get();
        assertEquals(stock.getStockPrice(), stock1.getStockPrice());
    }
    @Test
    public void saveStockFailure() throws Exception {
        Stock stock1 = null;
        if(stockRepo.findAll().toString().isEmpty()) {
            stockRepo.save(stock);
            stock1 = stockRepo.findById(stock.getTransactionId()).get();
        }
        assertNull(stock1);
    }
}
