package com.cts.CompanyManagementApp.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StockTest {
    Stock stock = Mockito.mock(Stock.class);
    @Test
    void testTransactionId() {
        when(stock.getTransactionId()).thenReturn(1L);
        assertEquals(1L, stock.getTransactionId());
    }
    @Test
    void testStockPrice() {
        when(stock.getStockPrice()).thenReturn(114.0);
        assertEquals(114.0,stock.getStockPrice());
    }
    @Test
    void testTimeStamp() {
        when(stock.getUpdatedAt()).thenReturn(new Date());
        assertEquals(new Date().toString(), stock.getUpdatedAt().toString());
    }
}
