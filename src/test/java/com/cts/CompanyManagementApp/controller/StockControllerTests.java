package com.cts.CompanyManagementApp.controller;

import com.cts.CompanyManagementApp.model.Company;
import com.cts.CompanyManagementApp.model.Stock;
import com.cts.CompanyManagementApp.service.CompanyServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class StockControllerTests {
    @Mock
    private CompanyServiceImpl companyService;
    @InjectMocks
    private StockController stockController;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }
    @Test
    public void addStockSuccess() throws Exception {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        company.setStockList(new ArrayList<>());
        Stock stock = new Stock();
        stock.setTransactionId(1L);
        stock.setStockPrice(114.5);
        stock.setUpdatedAt(new Date());

        when(companyService.getCompanyByCode(company.getCompanyCode())).thenReturn(company);
        when(companyService.updateCompany(company)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/stock//add/101").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stock))).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
