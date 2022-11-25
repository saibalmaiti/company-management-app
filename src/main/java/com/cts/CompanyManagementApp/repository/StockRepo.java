package com.cts.CompanyManagementApp.repository;

import com.cts.CompanyManagementApp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StockRepo extends JpaRepository<Stock, Long> {
    @Modifying
    @Query(value = "DELETE FROM Stock WHERE company_code_fk= :companyCode")
    public void deleteStock(long companyCode);
}
