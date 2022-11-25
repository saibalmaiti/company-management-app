package com.cts.CompanyManagementApp.service;

import com.cts.CompanyManagementApp.model.Stock;

public interface StockService {
    public boolean addStock(Stock stock);
    public boolean deleteStock(long companyCode);
}
