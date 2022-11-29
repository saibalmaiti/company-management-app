package com.cts.CompanyManagementApp.repository;

import com.cts.CompanyManagementApp.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@AutoConfigureMockMvc
public class CompanyRepoTests {
    @Autowired
    private CompanyRepo companyRepo;

    Company company = new Company();
    @BeforeEach
    public void init() {
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
    }
    @Test
    public void saveCompanySuccess() throws Exception {
        Company company1 = null;
        companyRepo.save(company);
        company1 = companyRepo.findById(company.getCompanyCode()).get();
        assertEquals(company.getCompanyName(), company1.getCompanyName());
    }
    @Test
    public void saveCompanyFailed() throws Exception {
        Company company1 = null;
        if(companyRepo.findAll().toString().isEmpty()) {
            companyRepo.save(company);
            company1 = companyRepo.findById(company.getCompanyCode()).get();
        }
        assertNull(company1);
    }
}
