package com.cts.CompanyManagementApp.model;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyTests {
    Company company = Mockito.mock(Company.class);
    @Test
    public void testCompanyCode() {
        when(company.getCompanyCode()).thenReturn(101L);
        assertEquals(101L, company.getCompanyCode());
    }
    @Test
    void testCompanyName() {
        when(company.getCompanyName()).thenReturn("Cognizant");
        assertEquals("Cognizant", company.getCompanyName());
    }
    @Test
    void testCompanyCeo() {
        when(company.getCompanyCeo()).thenReturn("B.H");
        assertEquals("B.H", company.getCompanyCeo());
    }
    @Test
    void testCompanyTurnover() {
        when(company.getCompanyTurnover()).thenReturn(12.0);
        assertEquals(12.0, company.getCompanyTurnover());
    }
    @Test
    void testCompanyWebsite() {
        when(company.getCompanyWebSite()).thenReturn("www.cognizant.com");
        assertEquals("www.cognizant.com", company.getCompanyWebSite());
    }
    @Test
    void testCompanyStockExchange() {
        when(company.getStockExchange()).thenReturn("BSE");
        assertEquals("BSE", company.getStockExchange());
    }
    @Test
    void testStockPrize() {
        when(company.getStockPrice()).thenReturn(114.0);
        assertEquals(114.0, company.getStockPrice());
    }
}
