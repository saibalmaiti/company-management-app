package com.cts.CompanyManagementApp.service;

import com.cts.CompanyManagementApp.model.Stock;
import com.cts.CompanyManagementApp.repository.StockRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepo stockRepo;
    @Override
    public boolean addStock(Stock stock) {
        stock.setStockPrice(stock.getStockPrice());
//        stock.setCompany_code_fk(stock.getCompany_code_fk());
        stockRepo.save(stock);
        return true;
    }

    @Override
    public boolean deleteStock(long companyCode) {
        stockRepo.deleteStock(companyCode);
        return true;
    }
}
