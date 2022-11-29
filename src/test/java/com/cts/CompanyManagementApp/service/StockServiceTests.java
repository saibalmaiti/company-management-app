package com.cts.CompanyManagementApp.service;

import com.cts.CompanyManagementApp.model.Stock;
import com.cts.CompanyManagementApp.repository.StockRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class StockServiceTests {
    @Mock
    private StockRepo stockRepo;
    @InjectMocks
    private StockServiceImpl stockService;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockService).build();
    }
    @Test
    public void addStockSuccess() {
        Stock stock = new Stock();
        stock.setTransactionId(1L);
        stock.setStockPrice(114.5);
        stock.setUpdatedAt(new Date());
        when(stockRepo.save(stock)).thenReturn(stock);
        assertTrue(stockService.addStock(stock));
    }
}
